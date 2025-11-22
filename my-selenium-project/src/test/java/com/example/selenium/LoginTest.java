package com.example.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.assertj.core.api.SoftAssertions;
import java.time.Duration;

public class LoginTest {

    static WebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.edge.driver", "A:\\Fall2025\\SW\\MySeleniumProject\\lib\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("file:///A:/Fall2025/SW/Lab/java_project/src/main/java/com/example/Lab2.html");
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Test Empty Username and Password (Expect 2 Alerts) (CT1)")
    public void testEmptyUsernameAndPassword() {
        SoftAssertions softly = new SoftAssertions();

        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        passwordField.clear();
        loginButton.click();

        Alert alert = driver.switchTo().alert();
        softly.assertThat(alert.getText()).isEqualTo("invalid empty name and password");
        alert.accept();
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Empty Username with Valid Password (DT3)")
    public void testEmptyUsername() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        passwordField.clear();
        passwordField.sendKeys("123456asb");
        loginButton.click();

        Alert alert = driver.switchTo().alert();
        softly.assertThat(alert.getText()).isEqualTo("invalid empty name");
        alert.accept();
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Empty Password with inValid Username (ET5)")
    public void testEmptyPassword() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("mohamedma");
        passwordField.clear();
        loginButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            softly.assertThat(alert.getText()).isEqualTo("invalid name pattern");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: The expected invalid name pattern alert");
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            softly.assertThat(alert.getText()).isEqualTo("invalid empty password");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: The expected invalid empty password alert");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Username Shorter than 8 Chars (FT6)")
    public void testInvalidUsernameLengthLessThan8() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("Mostafa");
        passwordField.sendKeys("most16");
        loginButton.click();

        Alert alert = driver.switchTo().alert();
        softly.assertThat(alert.getText()).isEqualTo("invalid  name length not 8 characters");
        alert.accept();
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Username Longer than 8 Chars (FT8)")
    public void testInvalidUsernameLengthGreaterThan8() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("Mostafaaa");
        passwordField.sendKeys("most16");
        loginButton.click();

        Alert alert = driver.switchTo().alert();
        softly.assertThat(alert.getText()).isEqualTo("invalid  name length not 8 characters");
        alert.accept();
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Username with Digits (GT9)")
    public void testUsernameContainsDigits() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("Abcd1234");
        passwordField.sendKeys("Abcd123");
        loginButton.click();

        Alert alert = driver.switchTo().alert();
        softly.assertThat(alert.getText()).isEqualTo("invalid name should contain only characters");
        alert.accept();
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Password Shorter than 6 Chars (BT3)")
    public void testInvalidPasswordTooShort() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("Ahmedmah");
        passwordField.sendKeys("ah12");
        loginButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            softly.assertThat(alert.getText()).isEqualTo("invalid  password length not 6 characters");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: invalid  password length not 6 characters alert");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Password with Letters Only (BT4)")
    public void testInvalidPasswordLettersOnly() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("Ahmedmah");
        passwordField.sendKeys("ahmedmah");
        loginButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            softly.assertThat(alert.getText()).isEqualTo("password pattern isn't correct");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: password pattern isn't correct alert");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Password with Numbers Only (BT5)")
    public void testInvalidPasswordNumbersOnly() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("Ahmedmah");
        passwordField.sendKeys("123456");
        loginButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            softly.assertThat(alert.getText()).isEqualTo("password pattern isn't correct");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: password pattern isn't correct alert");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Invalid Username Pattern AND Invalid Password Pattern (Expect 2 Alerts)")
    public void testInvalidUsernameAndInvalidPasswordPatterns() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("User123");
        passwordField.sendKeys("pass");
        loginButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            softly.assertThat(alert.getText()).isEqualTo("invalid name pattern");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: The expected invalid name pattern alert");
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            softly.assertThat(alert.getText()).isEqualTo("password pattern isn't correct");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: password pattern isn't correct alert");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Valid Login - All Lowercase Username (A:T1)")
    public void testCorrectLoginLowercaseUsername() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("abababab");
        passwordField.sendKeys("ahmed123");
        loginButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            softly.assertThat(alert.getText()).isEqualTo("invalid name pattern");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: invalid name pattern alert");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Valid Login - All Uppercase Username (A:T2)")
    public void testCorrectLoginUppercaseUsername() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("ABABABAB");
        passwordField.sendKeys("ahmed123");
        loginButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            softly.assertThat(alert.getText()).isEqualTo("invalid name pattern");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: invalid name pattern alert");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Test Valid Login - MixedCase Username (HT5)")
    public void testCorrectLoginScenario_HT5() {
        SoftAssertions softly = new SoftAssertions();
        WebElement usernameField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.clear();
        usernameField.sendKeys("asdvBGT#");
        passwordField.sendKeys("asdfgh23#5");
        loginButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            softly.assertThat(alert.getText()).isEqualTo("invalid name should contain only characters");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: invalid name should contain only characters alert");
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            softly.assertThat(alert.getText()).isEqualTo("password pattern isn't correct");
            alert.accept();
        } catch (TimeoutException e) {
            softly.fail("Test FAILED: password pattern isn't correct alert");
        }
        softly.assertAll();
    }
}