package com.test.automation.TimeDoctorLogin.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	
	public FileOutputStream fileOut;
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	
	public Excel_Reader(String path)
	{
		this.path = path;
		try
		{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/** 
	 * This Method will return 20 object data for each record in excel sheet
	 * 
	 * @param sheetName
	 * @param fileName
	 * @return
	 */
	
	@SuppressWarnings({"deprecation"})
	public String[][] getDataFromSheet(String excelName, String sheetName)
	{
		String dataSets[][] = null;
		try
		{
			//get sheet from the excel work book
			XSSFSheet sheet = workbook.getSheet(sheetName);
			//count the number of the active rows 
			int totalRow = sheet.getLastRowNum() + 1;
			//count the number of active columns in rows
			int totalColumn =sheet.getRow(0).getLastCellNum();
			//create array of row & column 
			dataSets = new String[totalRow-1][totalColumn];
			//run the loop & store the data in the 20 array.
			//This for loop runs on the row
			for (int i=1; i<totalRow; i++)
			{
				XSSFRow rows = sheet.getRow(i);
				//This for loop will runs on columns of that row.
				for(int j=0; j<totalColumn; j++)
				{
					//get cell method will get cell
					XSSFCell cell = rows.getCell(j);
					
					
					
					
					
					//if the cell of type string, then this if condition will work
					if(cell.getCellType()==cell.CELL_TYPE_STRING)
					{
						dataSets[i-1][j]=cell.getStringCellValue();
					}
					//if the cell of type Numeric, then this if condition will work
					else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
					{
						String cellText = String.valueOf(cell.getNumericCellValue());
						dataSets[i-1][j] = cellText;
					}
					//if the cell of type Boolean, then this if condition will work
					else
					{
						dataSets[i-1][j] = String.valueOf(cell.getBooleanCellValue());
					}
				}

			}
			return dataSets;
		}
		catch(Exception e)
		{
			System.out.println("Exception in Reading the Excel File" + e.getMessage());
			e.printStackTrace();
		}
		return dataSets;
	}
	
	
	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum)
	{
		try
		{
			int col_Num=0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for(int i=0; i<row.getLastCellNum(); i++)
			{
				if(row.getCell(i).getStringCellValue().equals(colName))
				{
					col_Num=i;
					break;
				}
			}
			row =sheet.getRow(rowNum-1);
			
			XSSFCell cell = row.getCell(col_Num);
			if(cell.getCellType()==cell.CELL_TYPE_STRING)
			{
				return cell.getStringCellValue();
			}
			else if(cell.getCellType()==cell.CELL_TYPE_BLANK)
			{
				return "";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
	// Read the data from selected row. 
	
	@SuppressWarnings({"deprecation"})
	public String[][] getDataFromSheetInSelectedRow(String excelName, String sheetName, int rowNum)
	{
		String dataSets[][] = null;
		
		try
		{
			//get sheet from the excel work book
			XSSFSheet sheet = workbook.getSheet(sheetName);
			//count the number of the active rows 
			int totalRow = sheet.getLastRowNum() + 1;
			//count the number of active columns in rows
			int totalColumn =sheet.getRow(0).getLastCellNum();
			//create array of row & column 
			dataSets = new String[rowNum][totalColumn];
			//run the loop & store the data in the 20 array.
			//This for loop runs on the row
			for (int i=1; i<totalRow; i++)
			{
				if(rowNum==i)
				{
					XSSFRow rows = sheet.getRow(i);
					//This for loop will runs on columns of that row.
					for(int j=0; j<totalColumn; j++)
					{
						//get cell method will get cell
						XSSFCell cell = rows.getCell(j);
						//if the cell of type string, then this if condition will work
						if(cell.getCellType()==cell.CELL_TYPE_STRING)
						{
						dataSets[i-1][j]=cell.getStringCellValue();
						}
						//if the cell of type Numeric, then this if condition will work
						else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
						{
							String cellText = String.valueOf(cell.getNumericCellValue());
							dataSets[i-1][j] = cellText;
						}
						//if the cell of type Boolean, then this if condition will work
						else
						{
							dataSets[i-1][j] = String.valueOf(cell.getBooleanCellValue());
						}
					}
					
				return dataSets;
				}

			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in Reading the Excel File" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
