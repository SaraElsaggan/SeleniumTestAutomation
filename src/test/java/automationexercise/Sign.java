package automationexercise;

import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Sign {

	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}

	@AfterMethod
	public void quit() {
			driver.quit();
	}

	@Test
	public void signIn() {
		// ________implicit wait________
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.linkText("Signup / Login")).click();
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("tes1234t@email.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Password123");
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");


	}


	@Test
	public void signUp() {

		// ________Explicit wait________

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login"))).click();


		driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(generateEmail());
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

		// ________Fluent wait________
		Wait<WebDriver> fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender2"))).click();

		driver.findElement(By.id("password")).sendKeys("123456");

		WebElement days = driver.findElement(By.id("days"));
		Select selectDay = new Select(days);
		selectDay.selectByIndex(3);

		WebElement months = driver.findElement(By.id("months"));
		Select selectMonth = new Select(months);
		selectMonth.selectByVisibleText("June");

		WebElement years = driver.findElement(By.id("years"));
		Select selectYear = new Select(years);
		selectYear.selectByValue("2000");


		driver.findElement(By.id("first_name")).sendKeys("fName");
		driver.findElement(By.id("last_name")).sendKeys("lName");
		driver.findElement(By.id("address1")).sendKeys("address");

		WebElement country = driver.findElement(By.id("country"));
		Select selectCountry = new Select(country);
		selectCountry.selectByIndex(1);

		driver.findElement(By.id("state")).sendKeys("cairo");
		driver.findElement(By.id("city")).sendKeys("cairo");
		driver.findElement(By.id("zipcode")).sendKeys("1234");
		driver.findElement(By.id("mobile_number")).sendKeys("123456789");
		driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(driver.findElement(By.xpath("//b")).getText(), "ACCOUNT CREATED!");
		soft.assertAll();

	}

	public  String generateEmail() {
		return  new Date().toString().replace(" ", "").replace(":", "")+"@gmail.com";
	}




}
