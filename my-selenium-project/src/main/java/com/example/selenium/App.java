package com.example.selenium;
import java.time.Duration;
/* 
import java.time.Duration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
*/
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.lang.Thread;
public class App {
    public static void main(String[] args) throws InterruptedException {
        /* 
        try {
            AppiumDriver<MobileElement> appiumDriver = null;
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554"); // غيّر لو لازم
            desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp"); // غيّر للـ package بتاعك
            desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability("appWaitActivity", "com.wdiodemoapp.MainActivity, com.wdiodemoapp.*");
            desiredCapabilities.setCapability("noReset", true);
            desiredCapabilities.setCapability("dontStopAppOnReset", true);
            desiredCapabilities.setCapability("skipDeviceInitialization", true);
            desiredCapabilities.setCapability("autoGrantPermissions", true);

            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AppiumDriver<>(appiumServer, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
            MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            MobileElement email = appiumDriver.findElementByAccessibilityId("input-email");
            email.sendKeys("thuyhn@maildomain.com");
            MobileElement password = appiumDriver.findElementByAccessibilityId("input-password");
            password.sendKeys("password");
            MobileElement loginButton = appiumDriver.findElementByAccessibilityId("button-LOGIN");
            loginButton.click();
            MobileElement alertMsg = appiumDriver.findElementById("android:id/message");
            System.out.println("Alert message: " + alertMsg.getText());
            MobileElement okBtn = appiumDriver.findElementById("android:id/button1");
            okBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        System.setProperty("webdriver.edge.driver", "A:\\Fall2025\\SW\\MySeleniumProject\\lib\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div[1]/div[1]/p/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
        link.click();  
            Set<String> handles = driver.getWindowHandles();
            Iterator<String> it = handles.iterator();
            String childWindow = it.next();
            driver.switchTo().window(childWindow);
            WebElement submit = driver.findElement(By.id("submit"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
            WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
            username.sendKeys("student");
            Thread.sleep(2000);
            WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            password.sendKeys("Password123");
            Thread.sleep(1000);
            submit.click();
            Thread.sleep(1000);
            driver.quit();



    }
}
        

