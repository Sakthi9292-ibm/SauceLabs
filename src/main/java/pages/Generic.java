package pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
public class Generic {
	
	public WebDriver drive;
	
	public Generic(WebDriver driver){
		
		this.drive = driver;
		System.out.println("generic"+this.drive.toString());
	}
	
	public void take_screenshot(String fprefix) throws IOException{
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String path = System.getProperty("user.dir")+"\\target\\output\\screenshots\\";
		File file=  ((TakesScreenshot) drive).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(path+fprefix+"_"+timestamp+"_screenshot.png"));
		
		
		
	}
	
	
	public byte[] attach_screenshot() throws IOException{
		byte[] file=  ((TakesScreenshot) drive).getScreenshotAs(OutputType.BYTES);
		return file;
		
		
		
	}
	
	
}
