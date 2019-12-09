package Utils;

import java.io.IOException;

public class EcelUtilsDemo {
	static String path1="G:\\SELENIUM\\Workspace\\eurekaonline\\src\\test\\resources\\Excel\\LoginDetails.xlsx";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
 
		ExcelUtils excel=new ExcelUtils(path1, "Sheet1");
		excel.getRowCount();
		excel.getColCount();
		excel.getCellDataString(1, 0);
		excel.getCellDataNumeric(1,1);
	}

}
