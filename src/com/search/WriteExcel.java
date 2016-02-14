package com.search;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.search.SearchResult;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel{
	
	public void write(HashMap<String, ArrayList<SearchResult>> results){
		String excelFileName = "data//Test.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;
		Set<String> sizeMap = results.keySet();
		int rownum = 0;
		//iterating r number of rows
		for (String searchWord : sizeMap){
			List<SearchResult> values = results.get(searchWord);
			for (SearchResult result: values){
				XSSFRow row = sheet.createRow(rownum++);
				XSSFCell searchWordCell = row.createCell(0);
				XSSFCell urlCell = row.createCell(1);
				XSSFCell titleCell = row.createCell(2);
				XSSFCell emailCell = row.createCell(3);
				
				searchWordCell.setCellValue(searchWord);
				urlCell.setCellValue(result.url);
				titleCell.setCellValue(result.title);
				emailCell.setCellValue(result.getEmailString());
			}
		}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(new File(excelFileName));
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}

