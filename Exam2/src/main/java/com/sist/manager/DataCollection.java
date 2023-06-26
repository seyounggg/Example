package com.sist.manager;

import java.sql.*;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sist.dao.*;
import com.sist.vo.*;

public class DataCollection {
	private WebDriver driver;
	private WebElement element;
	private String  url;
	
	public static String Web_driver_Id="webdriver.chrome.driver";
	public static String Web_driver_Path="/Users/seyeong/Desktop/Example/chromedriver";
	
	public DataCollection() {
		System.setProperty(Web_driver_Id,Web_driver_Path);
		driver=new ChromeDriver();
		url="https://www.mangoplate.com";
	}
	
	public void detailData() {
		MangoDAO dao=MangoDAO.newInstance();
		List<CategoryVO> list=dao.CategoryData();
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 10); // 최대 10초간 대기
			for(CategoryVO vo:list) {
				driver.get(url+vo.getLink());
				Thread.sleep(500); //0.5초 기다리기
				for (int li = 1; li <= 10; li++) {
	                // div 번갈아가며 반복 (1과 2)
	                for (int div = 1; div <= 2; div++) {
	                	WebElement link2=driver.findElement(By.xpath("/html/body/main/article/div[2]/div/div/section/div[3]/ul/li["+li+"]/div["+div+"]/figure/a"));
	                	//WebElement link2=driver.findElement(By.xpath("xpath"));
	                    String href = link2.getAttribute("href");
	                    System.out.println("href: " + href);
	                }
				}
			}
			System.out.println("출력완료!!");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DataCollection dc=new DataCollection();
		dc.detailData();

	}
}
