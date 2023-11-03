package WeekDayBooking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WeekDayBooking 
{
	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	public static void main(String[] args) throws InterruptedException 
	{
		LOGGER.info("Booking started at" + new java.util.Date());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://book.everyoneactive.com/Connect/mrmselectsite.aspx?disableSiteSelection=1");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("ctl00_MainContent_InputLogin")).sendKeys("nags.sellaiah@gmail.com");
		driver.findElement(By.id("ctl00_MainContent_InputPassword")).sendKeys("Active@2023");
		driver.findElement(By.id("ctl00_MainContent_btnLogin")).click();
		twoWeeksForward(driver);
		Thread.sleep(3000);
		int timeStampMinutes = Integer.parseInt(new SimpleDateFormat("mm").format(new java.util.Date()));// 55
		int timeStampSeconds = Integer.parseInt(new SimpleDateFormat("ss").format(new java.util.Date()));// 10
		if (timeStampSeconds > 0) {
			timeStampMinutes = timeStampMinutes + 1;
			timeStampSeconds = 60 - timeStampSeconds;
		}
		int timeDiffMin = 60 - timeStampMinutes;
		int secDiff = timeDiffMin * 60 + timeStampSeconds;
		int milliSecondsDelay = secDiff * 1000;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		
		Date EndTime = null;
		try {
			EndTime = dateFormat.parse("00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(milliSecondsDelay);
		
		Date CurrentTime = null;
		try {
			CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (CurrentTime.equals(EndTime))
		{
		    driver.findElement(By.id("ctl00_MainContent_dateForward1")).click();
		    if(day == 1 || day == 7)
		    {
		    	/* Week day 8:00 AM and 9:00 AM Booking */
		    	weekEndMorningBooking(driver);
		    }
		    else
		    {
		    	/* Week day 7:30 PM and 8:30 PM Booking */
		    	weekDayEveningBooking(driver);
			    
			    /* Week day 14:30 PM and 15:30 PM Booking */
			    //midDayBookingTest(driver);
		    }
		}
		driver.quit();
	}
	public static void twoWeeksForward(WebDriver driver) 
	{
		driver.get("https://book.everyoneactive.com/Connect/mrmselectsite.aspx?disableSiteSelection=1");
		driver.findElement(By.xpath("//input[@value='Sports Hall']")).click();
		driver.findElement(By.xpath("//input[@value='Badminton 55 Min']")).click();
		//driver.findElement(By.id("ctl00_MainContent_activitiesGrid_ctrl0_lnkListCommand")).click();
		driver.findElement(By.id("ctl00_MainContent_dateForward1")).click();
	}
	public static void midDayBookingTest(WebDriver driver)
	{
		try 
		  { 
			  LOGGER.info("Booking started for 14:30");
			  driver.findElement(By.id("ctl00_MainContent_cal_calbtn56")).click();//		  ctl00_MainContent_cal_calbtn56 --14:30
			  driver.findElement(By.xpath("(//input[@value='14:30'])[1]")).click();
			  driver.findElement(By.xpath("//input[@value='Book']")).click(); 
		  }
		  catch(ElementClickInterceptedException ex) { throw ex; }
		  twoWeeksForward(driver);
		  driver.findElement(By.id("ctl00_MainContent_dateForward1")).click();
		  
		  //15:30 PM Booking 
		  try 
		  { 
			  LOGGER.info("Booking started for 20:30");
		      Thread.sleep(3000);
		      driver.findElement(By.id("ctl00_MainContent_cal_calbtn63")).click();//		  ctl00_MainContent_cal_calbtn63 -- 15:30
		      driver.findElement(By.xpath("(//input[@value='15:30'])[1]")).click();
		      driver.findElement(By.xpath("//input[@value='Book']")).click();
		  }
		  catch(ElementClickInterceptedException ex) { throw ex; } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//7:30 PM to 8:30 PM Booking
	public static void weekDayEveningBooking(WebDriver driver)
	{
		try 
		{ 
			 LOGGER.info("Booking started for 19:30");
			 driver.findElement(By.id("ctl00_MainContent_cal_calbtn91")).click();
			 driver.findElement(By.xpath("(//input[@value='19:30'])[1]")).click();
			 driver.findElement(By.xpath("//input[@value='Book']")).click(); 
		}
		catch(ElementClickInterceptedException ex) { throw ex; }
		twoWeeksForward(driver);
		driver.findElement(By.id("ctl00_MainContent_dateForward1")).click();
		
		//8:30 PM Booking 
		try 
		{
			 LOGGER.info("Booking started for 20:30");
			 Thread.sleep(3000);
			 driver.findElement(By.id("ctl00_MainContent_cal_calbtn98")).click();
			 driver.findElement(By.xpath("(//input[@value='20:30'])[1]")).click();
			 driver.findElement(By.xpath("//input[@value='Book']")).click(); 
		}
		catch(ElementClickInterceptedException ex) { throw ex; } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void weekEndMorningBooking(WebDriver driver)
	{
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
		twoWeeksForward(driver);
		driver.findElement(By.id("ctl00_MainContent_dateForward1")).click();
		/* 8 AM Booking */
		try
		{
			Thread.sleep(3000);
			driver.findElement(By.id("ctl00_MainContent_cal_calbtn14")).click();//ctl00_MainContent_cal_calbtn70
			driver.findElement(By.xpath("(//input[@value='08:00'])[1]")).click();
			driver.findElement(By.xpath("//input[@value='Book']")).click();
		}
		catch(ElementClickInterceptedException ex)
		{
			throw ex;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
