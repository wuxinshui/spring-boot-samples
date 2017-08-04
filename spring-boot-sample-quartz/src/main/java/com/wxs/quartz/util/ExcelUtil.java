package com.wxs.quartz.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Description:
 * @Author:FujiRen
 * @Date:2017/4/20 11:30
 */
public class ExcelUtil {

	/**
	 * 生成Excel
	 *
	 * @return
	 */
	public static Workbook createExcel() {
		//new HSSFWorkbook() make xls
		//new XSSFWorkbook() make xlsx
		Workbook xlsx = new XSSFWorkbook();

		Sheet sheet = xlsx.createSheet();
		Row row = sheet.createRow(1);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("0");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("1");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("2");

		return xlsx;
	}
}
