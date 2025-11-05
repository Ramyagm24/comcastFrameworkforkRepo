package com.webshop.crm.FileUttility;

import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcelFile(String sheet,int rownum,int cellnum) throws Throwable {
		FileInputStream fis=new FileInputStream("./testscriptdata/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(rownum).getCell(cellnum).getStringCellValue();
		return data;
	}
	public int getRowCount(String sheetname) throws Throwable {
		FileInputStream fis=new FileInputStream("./testscriptdata/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetname).getLastRowNum();
		return rowcount;
	}
	public void setDataIntoExcel(String sheetname,int rowNum,int cellnum,String data) throws Throwable {
		FileInputStream fis=new FileInputStream("./testscriptdata/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetname).createRow(rowNum).createCell(cellnum).setCellValue(data);
		
		 FileOutputStream fos=new FileOutputStream("./testscriptdata/testscriptdata.xlsx");
		 wb.write(fos);
		 wb.close();
	}
	
	public Cell getcell(String sheetname,int rowNum,int cellnum) throws Throwable {
		FileInputStream fis=new FileInputStream("./testscriptdata/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Cell cell = wb.getSheet(sheetname).getRow(rowNum).getCell(cellnum);
		
		 
		 wb.close();
		return cell;
	}
	public void writeDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws Throwable {
	    FileInputStream fis = new FileInputStream("path/to/excel.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    Sheet sh = wb.getSheet(sheetName);
	    Row row = sh.getRow(rowNum);
	    if (row == null) row = sh.createRow(rowNum);
	    Cell cell = row.createCell(cellNum);
	    cell.setCellValue(data);
	    FileOutputStream fos = new FileOutputStream("path/to/excel.xlsx");
	    wb.write(fos);
	    wb.close();
	}
}
