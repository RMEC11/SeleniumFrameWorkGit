package BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestcaseBase {
	
	Properties property;
	FileInputStream fip;
	
	@Test
	public void  testProperty() throws IOException {
		
		
		fip=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
	
		property=new Properties();
		property.load(fip);
		
		System.out.println(property.getProperty("browser"));
		System.out.println(property.getProperty("baseUrl"));
		WebDriverManager.chromedriver().setup(); 
		WebDriver driver=new ChromeDriver();
		driver.get("http://3deureka.in:81/");
		driver.manage().window().maximize();
		driver.quit();
	}
	

}
