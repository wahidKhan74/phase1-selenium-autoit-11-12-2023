package com.simplilearn.seleniumtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class demonstrate selenium + autoit integration
 * 
 * @author khanw
 *
 */
public class UploadProfileImgToGithubTest {

	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) throws InterruptedException, IOException {

		setUp();
		
		Thread.sleep(2000);
		
		login();
		
		Thread.sleep(2000);
		
		uploadProfile();
		
		Thread.sleep(2000);
	}
	
	public static void setUp() {

		// step1: formulate a test domain url & driver path
		String siteUrl = "https://github.com/login";
		String driverPath = "drivers/windows/chromedriver.exe";

		// step2: set system properties for selenium dirver
		System.setProperty("webdriver.chrome.driver", driverPath);

		// step3: instantiate selenium webdriver
		driver = new ChromeDriver();

		// step4: add explicit wait (Conditional Delay)
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		// step5: launch browser
		driver.get(siteUrl);
	}
	
	public static void login() {
		// find elements and enter creds
		driver.findElement(By.id("login_field")).sendKeys(Login.username);
		driver.findElement(By.id("password")).sendKeys(Login.password);
		
		driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[13]")).submit();
	}

	public static void uploadProfile() throws IOException, InterruptedException {
		
		// redirect to profile page
		String siteUrl = "https://github.com/settings/profile";
		driver.get(siteUrl);
		
		// click on profile edit
		driver.findElement(By.xpath("//*[@id=\"settings-frame\"]/div[2]/div[2]/dl/dd/div/details/summary/div")).click();
		
		// click on upload photo 
		driver.findElement(By.xpath("//*[@id=\"settings-frame\"]/div[2]/div[2]/dl/dd/div/details/details-menu/label")).click();
		
		
		Thread.sleep(3000);
		
		// run autoit exe file 
		Runtime.getRuntime().exec("autoit\\upload-profile.exe");
		
		
		Thread.sleep(3000);
		
		// click on final submit
		driver.findElement(By.xpath("//*[@id=\"avatar-crop-form\"]/div[2]/button")).click();
		
	}
	
}
