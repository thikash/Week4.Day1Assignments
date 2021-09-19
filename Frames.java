package assignmentWeek4.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement frame1 = driver.findElementById("frame1");
		driver.switchTo().frame(frame1);
		driver.findElementByXPath("//input[@type='text']").sendKeys("TestNG FrameWork");
		WebElement frame2 = driver.findElementById("frame3");
		driver.switchTo().frame(frame2);
		driver.findElementByXPath("//input[@id='a']").click();
		driver.switchTo().defaultContent();
		WebElement frame3 = driver.findElementById("frame2");
		driver.switchTo().frame(frame3);

		WebElement select = driver.findElementById("animals");
		Select s = new Select(select);
		s.selectByValue("avatar");
	}

}
