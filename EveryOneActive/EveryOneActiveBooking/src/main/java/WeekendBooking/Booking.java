package WeekendBooking;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.chrome.ChromeDriver;

public class Booking 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//Date today = new Date();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://book.everyoneactive.com/Connect/mrmselectsite.aspx?disableSiteSelection=1");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("ctl00_MainContent_InputLogin")).sendKeys("nags.sellaiah@gmail.com");
		driver.findElement(By.id("ctl00_MainContent_InputPassword")).sendKeys("Active@2023");
		driver.findElement(By.id("ctl00_MainContent_btnLogin")).click();
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		twoWeeksForward(driver);
		Thread.sleep(3000);
		int timeStampMinutes = Integer.parseInt(new SimpleDateFormat("mm").format(new java.util.Date()));//55
		int timeStampSeconds = Integer.parseInt(new SimpleDateFormat("ss").format(new java.util.Date()));//10
		if(timeStampSeconds >0)
		{
			timeStampMinutes = timeStampMinutes-1;
		}
		int timeDiffMin = 60 - timeStampMinutes;
		int secDiff = timeDiffMin * 60 +timeStampSeconds;
		int milliSecondsDelay =  secDiff *1000;
		Thread.sleep(milliSecondsDelay);
		driver.findElement(By.id("ctl00_MainContent_dateForward1")).click();
		if(day == 1) // SUNDAY
		{
			/* 9 AM Booking */
			try
			{
				driver.findElement(By.id("ctl00_MainContent_cal_calbtn21")).click();//ctl00_MainContent_cal_calbtn70
				driver.findElement(By.xpath("(//input[@value='09:00'])[1]")).click();
				driver.findElement(By.xpath("//input[@value='Book']")).click();
			}
			catch(ElementClickInterceptedException ex)
			{
				throw ex;
			}
			driver.get("https://book.everyoneactive.com/Connect/mrmselectsite.aspx?disableSiteSelection=1");
			twoWeeksForward(driver);
			/* 8 AM Booking */
			try
			{
				driver.findElement(By.id("ctl00_MainContent_cal_calbtn14")).click();//ctl00_MainContent_cal_calbtn70
				driver.findElement(By.xpath("(//input[@value='08:00'])[1]")).click();
				driver.findElement(By.xpath("//input[@value='Book']")).click();
			}
			catch(ElementClickInterceptedException ex)
			{
				throw ex;
			}
		}
		else if(day == 7) // SATURDAY
		{
			/* 9 AM Booking */
			try
			{
				driver.findElement(By.id("ctl00_MainContent_cal_calbtn21")).click();//ctl00_MainContent_cal_calbtn70
				driver.findElement(By.xpath("(//input[@value='09:00'])[3]")).click();
				driver.findElement(By.xpath("//input[@value='Book']")).click();
			}
			catch(ElementClickInterceptedException ex)
			{
				throw ex;
			}
			driver.get("https://book.everyoneactive.com/Connect/mrmselectsite.aspx?disableSiteSelection=1");
			twoWeeksForward(driver);
			/* 8 AM Booking */
			try
			{
				driver.findElement(By.id("ctl00_MainContent_cal_calbtn14")).click();//ctl00_MainContent_cal_calbtn70
				driver.findElement(By.xpath("(//input[@value='08:00'])[3]")).click();
				driver.findElement(By.xpath("//input[@value='Book']")).click();
			}
			catch(ElementClickInterceptedException ex)
			{
				throw ex;
			}
		}
			
		
		/* 10 AM Booking */
		/*
		driver.findElement(By.id("ctl00_MainContent_cal_calbtn28")).click();//ctl00_MainContent_cal_calbtn70
		driver.findElement(By.xpath("(//input[@value='10:00'])[1]")).click();
		driver.findElement(By.xpath("//input[@value='Book']")).click();
		System.out.println("Court 1 booked for 10 AM");
		driver.get("https://book.everyoneactive.com/Connect/mrmselectsite.aspx?disableSiteSelection=1");
		twoWeeksForward(driver);
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContent_cal_calbtn35")).click();//ctl00_MainContent_cal_calbtn70
		driver.findElement(By.xpath("(//input[@value='11:00'])[1]")).click();
		driver.findElement(By.xpath("//input[@value='Book']")).click();
		System.out.println("Court 1 booked for 11 AM");
		*/
	}
	public static void twoWeeksForward(ChromeDriver driver)
	{
		driver.get("https://book.everyoneactive.com/Connect/mrmselectsite.aspx?disableSiteSelection=1");
		driver.findElement(By.id("ctl00_MainContent_activityGroupsGrid_ctrl10_lnkListCommand")).click();
		driver.findElement(By.id("ctl00_MainContent_activitiesGrid_ctrl0_lnkListCommand")).click();
		driver.findElement(By.id("ctl00_MainContent_dateForward1")).click();
	}
}
