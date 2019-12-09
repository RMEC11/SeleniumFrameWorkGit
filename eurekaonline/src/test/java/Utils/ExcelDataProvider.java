package Utils;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataProvider {
	WebDriver driver;
	Properties property;
	FileInputStream fip;

	@Parameters("browser")
	@BeforeTest
	public void launch_browser(String browser) throws IOException {
		driver = null;

		fip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");

		property = new Properties();
		property.load(fip);
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().version("77.0.3865.40").setup();
			driver = new ChromeDriver();

		} else {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

	}

	@Test(dataProvider = "testData")
	public void test1(String firstName, String lastName, String username, String password) throws InterruptedException {
		System.out.println(firstName + "|" + lastName + "|" + username + "|" + password);
		driver.get("http://3deureka.in/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.id("signInClick")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("txtUserName")).sendKeys(username);
		driver.findElement(By.id("txtPwd")).sendKeys(password);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/form/input[4]")).click();
		//Thread.sleep(2000);
		if (driver.findElement(By.id("loginStatusMsg")).getText().contains("User not found")) {
			System.out.println(driver.findElement(By.id("loginStatusMsg")).getText());
			Assert.assertTrue(false, driver.findElement(By.id("loginStatusMsg")).getText());
		} else {
			System.out.println("User login successfully");
		}

	}

	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException {
		String path = "G:\\SELENIUM\\Workspace\\eurekaonline\\src\\test\\resources\\Excel\\LoginDetails.xlsx";
		Object data[][] = testData(path, "Sheet1");
		return data;

	}

	public static Object[][] testData(String excelPath, String sheetName) throws IOException {

		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		Object data[][] = new Object[rowCount - 1][colCount];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String cellData = excel.getCellDataString(i, j);
				data[i - 1][j] = cellData;

				System.out.print(cellData + " | ");
			}
			System.out.println();
		}
		return data;
	}

	@AfterTest
	public void close_browser() {
		// driver.close();
		driver.quit();
		System.out.println("Searched successfully");

	}

}
