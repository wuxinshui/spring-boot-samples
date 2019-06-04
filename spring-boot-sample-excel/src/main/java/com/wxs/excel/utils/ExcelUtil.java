package com.wxs.excel.utils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static Pattern pattern = Pattern.compile("^//d+(//.//d+)?$");
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static void exportExcel(Map<String, Collection<?>> dataSet, Map<String, Map<String, String>> headerMap, Map<String, String> additional, OutputStream out) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            int count = 0;
            for (Map.Entry<String, Collection<?>> entry : dataSet.entrySet()) {
                HSSFSheet sheet = workbook.createSheet();
                workbook.setSheetName(count, entry.getKey());
                if (CollectionUtils.isEmpty(additional) || !Boolean.TRUE.toString().equals(additional.get(entry.getKey()))) {
                    additional = null;
                } else {
                    additional.remove(entry.getKey());
                }
                fillSheet(workbook, sheet, entry.getValue(), headerMap.get(entry.getKey()), 0, entry.getValue().size(), additional);
                count++;
            }
            workbook.write(out);
        } catch (Exception e) {
            throw new RuntimeException("解析错误");
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.error("close workbook error", e);
            }
        }
    }

    private static <T> void fillSheet(HSSFWorkbook workbook, HSSFSheet sheet, Collection<T> data, Map<String, String> headerMap, int first, int last, Map<String, String> additional) throws IllegalAccessException {

        sheet.setDefaultColumnWidth(15);
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        String[] model = new String[headerMap.size()];
        String[] header = new String[headerMap.size()];
        int count = 0;
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            model[count] = entry.getKey();
            header[count] = entry.getValue();
            count++;
        }

        int rowNum = 0;
        if (!CollectionUtils.isEmpty(additional)) {
            generateDetail(workbook, sheet, additional);
            rowNum = additional.size() + 2;
        }

        HSSFRow row = sheet.createRow(rowNum);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(getStyle(workbook, true));
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
        }

        Iterator<T> it = data.iterator();
        for (int i = first; i < last; i++) {
            HSSFRow valueRow = sheet.createRow(rowNum + i + 1);
            T item = it.next();
            for (int j = 0; j < model.length; j++) {
                HSSFCell cell = valueRow.createCell(j);
                cell.setCellStyle(getStyle(workbook, false));
                Object value = getFieldValue(model[j], item);
                if (value == null) {
                    value = "";
                }
                setCellValue(cell, value, j, patriarch, valueRow, workbook, sheet);
            }
        }
    }

    private static void generateDetail(HSSFWorkbook workbook, HSSFSheet sheet, Map<String, String> additional) {
        int i = 0;
        for (Map.Entry<String, String> entry : additional.entrySet()) {
            HSSFRow row = sheet.createRow(i);
            if (!StringUtils.isEmpty(entry.getKey())) {
                HSSFCell cell = row.createCell(0);
                cell.setCellStyle(getStyle(workbook, true));
                HSSFRichTextString text = new HSSFRichTextString(entry.getKey());
                cell.setCellValue(text);
            }
            if (!StringUtils.isEmpty(entry.getValue())) {
                HSSFCell cell = row.createCell(1);
                cell.setCellStyle(getStyle(workbook, true));
                HSSFRichTextString text = new HSSFRichTextString(entry.getValue());
                cell.setCellValue(text);
            }
            i++;
        }
    }

    private static HSSFCellStyle getStyle(HSSFWorkbook workbook, boolean isBold) {
        HSSFCellStyle style = workbook.createCellStyle();
        if (isBold) {
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(isBold);
        style.setFont(font);
        return style;
    }

    private static Object getFieldValue(String fieldName, Object o) throws IllegalAccessException {
        Object value;
        String[] fieldNames = fieldName.split("\\.");
        if (fieldNames.length == 1) {
            value = getFieldValueByName(fieldName, o);
        } else {
            Object fieldObj = getFieldValueByName(fieldNames[0], o);
            String subFieldName = fieldName.substring(fieldName.indexOf('.') + 1);
            value = getFieldValue(subFieldName, fieldObj);
        }
        return value;
    }

    private static Object getFieldValueByName(String fieldName, Object o) throws IllegalAccessException {

        Field newField = getFieldByName(fieldName, o.getClass());

        if (newField != null) {
            newField.setAccessible(true);
            return newField.get(o);
        }
        return null;
    }

    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        Field[] selfFields = clazz.getDeclaredFields();

        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }

        return null;
    }

    private static void setCellValue(Cell cell, Object value, int index, HSSFPatriarch patriarch, HSSFRow row, HSSFWorkbook workbook, HSSFSheet sheet) {
        String textValue = null;
        if (value instanceof Integer) {
            int intValue = (Integer) value;
            cell.setCellValue(intValue);
        } else if (value instanceof Float) {
            float fValue = (Float) value;
            cell.setCellValue(fValue);
        } else if (value instanceof Double) {
            double dValue = (Double) value;
            cell.setCellValue(dValue);
        } else if (value instanceof Long) {
            long longValue = (Long) value;
            cell.setCellValue(longValue);
        } else if (value instanceof Boolean) {
            textValue = String.valueOf(value);
        } else if (value instanceof Date) {
            Date date = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            textValue = sdf.format(date);
        } else if (value instanceof byte[]) {
            // 有图片时，设置行高为60px
            row.setHeightInPoints(60);
            // 设置图片所在列宽度为80px,注意这里单位的一个换算
            sheet.setColumnWidth(index, (short) (35.7 * 80));
            byte[] bsValue = (byte[]) value;
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                    1023, 255, (short) 6, index, (short) 6, index);
            anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_DONT_RESIZE);
            patriarch.createPicture(anchor, workbook.addPicture(
                    bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
        } else {
            // 其它数据类型都当作字符串简单处理
            textValue = value.toString();
        }
        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
        if (textValue != null) {
            Matcher matcher = pattern.matcher(textValue);
            if (matcher.matches()) {
                // 是数字当作double处理
                cell.setCellValue(Double.parseDouble(textValue));
            } else {
                HSSFRichTextString richString = new HSSFRichTextString(textValue);
                cell.setCellValue(richString);
            }
        }
    }
}
