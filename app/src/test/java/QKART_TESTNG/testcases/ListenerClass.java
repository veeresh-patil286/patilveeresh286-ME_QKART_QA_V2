package QKART_TESTNG.testcases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.time.LocalDateTime;

public class ListenerClass implements ITestListener {

    private WebDriver driver;

    
    @Override
    public void onTestStart(ITestResult result) {
        if (driver == null) {
            driver = new ChromeDriver(); 
        }
        takeScreenshot(driver, "Start", result.getName()); 
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(driver, "End", result.getName());  
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(driver, "Failure", result.getName());  
    }

    public static void takeScreenshot(WebDriver driver, String screenshotType, String description) {
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();  
            }

            String timestamp = String.valueOf(LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, description);

            TakesScreenshot scrShot = (TakesScreenshot) driver;
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);  
            System.out.println("Screenshot saved at: " + DestFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
}
