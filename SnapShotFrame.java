package assignmentWeek4.day1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapShotFrame {

	public static void main(String[] args) throws IOException {
		
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new ChromeDriver();
driver.get("http://leafground.com/pages/frame.html");
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

WebElement frame1 = driver.findElementByXPath("//div[@id='wrapframe']/iframe");
	driver.switchTo().frame(frame1);
	WebElement click = driver.findElementByXPath("//button[@id='Click']");
	click.click();
	String text = click.getText();
	System.out.println(text);
	File src = click.getScreenshotAs(OutputType.FILE);
	File dst = new File("./snaps/clickframe.png");
	FileUtils.copyFile(src, dst);
	driver.switchTo().defaultContent();
	
	List<WebElement> frameList = driver.findElementsByTagName("iframe");
	int noofFrame = frameList.size();
	System.out.println("The Number of frames present is "+noofFrame);
	
	
	}

}
