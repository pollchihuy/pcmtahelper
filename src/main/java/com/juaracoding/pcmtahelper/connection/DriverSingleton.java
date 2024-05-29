package com.juaracoding.pcmtahelper.connection;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

	private static DriverSingleton instance = null;
	private static WebDriver driver;
	private DriverSingleton(String driver)
	{
		instantiate(driver);
	}
	
	public WebDriver instantiate(String strategy)
	{
		DriverStrategy driverStrategy = DriverStrategyExecution.chooseStrategy(strategy);
		driver = driverStrategy.setStrategy();
		driver.manage().timeouts().implicitlyWait(Constants.TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public static DriverSingleton getInstance(String driver)
	{
		if(instance == null) {
			instance = new DriverSingleton(driver);
		}
		
		return instance;
	}	
	
	public static WebDriver getDriver() 
	{
		return driver;
	}
	
	public static void closeObjectInstance() 
	{
        instance = null;
        try{
            driver.quit();
            } catch(NullPointerException e){
            	System.out.println("END!!");
//            	getInstance(Constants.CHROME);
            }
//        driver.quit();
    }
}