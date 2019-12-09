package Utils;

import java.io.IOException;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) throws IOException {
		workbook = new XSSFWorkbook(excelPath);
		sheet = workbook.getSheet(sheetName);

	}

	public static int getRowCount() throws IOException {
		int rowCount = 0;
		rowCount = sheet.getPhysicalNumberOfRows();
	     System.out.println("Row Count:" + rowCount);
		return rowCount;
	}

	public static String getCellDataString(int rowNum, int colNum) throws IOException {
		String cellData = null;
		cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		//System.out.println(cellData);
		return cellData;

	}
	
	public static double getCellDataNumeric(int rowNum, int colNum) throws IOException {
		double cellData = 0;
		cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
		//System.out.println(cellData);
		return cellData;

	}

	public static int getColCount() throws IOException {
		int colCount = 0;
		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Col Count:" + colCount);
		return colCount;
	}

}
