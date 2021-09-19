package assignmentWeek4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new ChromeDriver();
driver.get("https://dev82068.service-now.com");
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().window().maximize();
WebElement frame1 = driver.findElementByXPath("//iframe[@id='gsft_main']");
driver.switchTo().frame(frame1);
//Enter the username as admin
driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
//Enter the password as Aathi&&1234
driver.findElementById("user_password").sendKeys("Aathi&&1234");
//click Login
driver.findElementByXPath("//button[@id='sysverb_login']").click();
//search incident Filter Navigator
WebElement i = driver.findElementByXPath("//input[@id='filter']");
i.sendKeys("incident",Keys.PAGE_DOWN);

//Click All
Thread.sleep(3000);
driver.findElementByXPath("(//div[@class='sn-widget-list-title' and text()='All'])[2]").click();
//click new button
WebElement frame2 = driver.findElementByXPath("//iframe[@id='gsft_main']");
driver.switchTo().frame(frame2);
driver.findElement(By.id("sysverb_new")).click();

//Select a value for caller 
driver.findElementById("lookup.incident.caller_id").click();
Set<String> windowHandlesSet = driver.getWindowHandles();

List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
driver.switchTo().window(windowHandlesList.get(1));
System.out.println(driver.getTitle());
System.out.println("The number of windows "+windowHandlesList.size());
driver.findElementByXPath("(//a[@class='glide_ref_item_link'])[2]").click();
driver.switchTo().window(windowHandlesList.get(0));
System.out.println(driver.getTitle());
Thread.sleep(2000);
driver.switchTo().frame(frame2);

//Enter value for short description
driver.findElementById("incident.short_description").sendKeys("Boat headphone one side not working");

//Read the incident number and save it a variable
String incidentNumber = driver.findElementById("incident.number").getAttribute("value");
System.out.println("The Incident Number is "+incidentNumber);

//Click on SubmitButton
driver.findElementById("sysverb_insert_bottom").click();

//Search the same incident number in the next search screen as below
WebElement search = driver.findElementByXPath("(//input[@class='form-control'])[1]");
	search.sendKeys(incidentNumber);
	search.sendKeys(Keys.ENTER);
	
	//Verify the incident is created successful
	String resultIncidentNumber = driver.findElementByXPath("//a[@class='linked formlink']").getText();
	if(incidentNumber.equals(resultIncidentNumber)) {
		System.out.println("Incident created is successful");
	}
	else {
		System.out.println("****Error!**** Incident is Wrong");
	}
	//Take ScreenShot of Created incident
	File src = driver.getScreenshotAs(OutputType.FILE);
	File dst = new File("./snap/IncidentNumber.png");
	FileUtils.copyFile(src, dst);
	}

}
