package com.selenium.exam;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.vo.CategoryVO;

public class Main {
	private WebDriver driver;
	private WebElement element;
	private String  url;
	
	public static String Web_driver_Id="webdriver.chrome.driver";
	public static String Web_driver_Path="/Users/seyeong/Desktop/Example/chromedriver";
	
	public Main() {
		System.setProperty(Web_driver_Id,Web_driver_Path);
		driver=new ChromeDriver();
		url="https://www.mangoplate.com/search/제주";
	}
	
	public List<CategoryVO> CategoryData(){
		List<CategoryVO> list=new ArrayList<CategoryVO>();
		try {
			driver.get(url);
			String categoryXPath="/html/body/main/article/div[2]/div/div/section/div[1]/div[2]/section/div/a";
			List<WebElement> elements=driver.findElements(By.xpath(categoryXPath));
			for(WebElement e:elements) {
				String title=e.getText();
				String link=e.getAttribute("href");
				
				CategoryVO vo=new CategoryVO();
				vo.setTitle(title);
				vo.setLink(link);
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
		return list;
	}
	
	public static void main(String[] args) {
		Main m=new Main();
		List<CategoryVO> list=m.CategoryData();
		for(CategoryVO vo:list) {
			System.out.println("title: "+vo.getTitle());
			System.out.println("link: "+vo.getLink());
		}
	}
}
