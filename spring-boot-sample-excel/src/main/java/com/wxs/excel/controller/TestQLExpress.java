package com.wxs.excel.controller;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/3 18:55
 */
public class TestQLExpress {
    public static void main(String[] args) throws Exception {
        InputStream fis = new FileInputStream("E:\\project\\platform\\店东贷\\22.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis); //or new XSSFWorkbook("/somepath/test.xls")
        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

// suppose your formula is in B3
        CellReference cellReference = new CellReference("I3");
        Row row = sheet.getRow(cellReference.getRow());
        //城市
        row.getCell(2).setCellValue("郑州");
        //近6个月二手房+租赁实收业绩
        row.getCell(3).setCellValue("20000000.00");
        //近6个月新房认购业绩
        row.getCell(4).setCellValue("222.00");
        //店铺面积
        row.getCell(5).setCellValue("333");
        //A0级别员工数量
        row.getCell(6).setCellValue("2");
        //征信月负债
        row.getCell(7).setCellValue("11");
        Cell cell = row.getCell(cellReference.getCol());
        if (cell != null) {
            switch (evaluator.evaluateFormulaCell(cell)) {
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    System.out.println(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    System.out.println(cell.getErrorCellValue());
                    break;

                // CELL_TYPE_FORMULA will never occur
                case Cell.CELL_TYPE_FORMULA:
                    break;
            }
        }
    }
}
