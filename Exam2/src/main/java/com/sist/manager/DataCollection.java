package com.sist.manager;

import java.sql.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		
		List<String> hrefList = new ArrayList<String>();
		Set<String> uniqueHrefSet = new HashSet<String>();
		int index = 0;

		try {
		    //WebDriverWait wait = new WebDriverWait(driver, 10); // 최대 10초간 대기
		    for (CategoryVO vo : list) {
		        String link = url + vo.getLink();
		        driver.get(link);
		        Thread.sleep(500); // 0.5초 기다리기

		        hrefList.clear();
		        uniqueHrefSet.clear();

		        for (int li = 1; li <= 10; li++) {
		            // div 번갈아가며 반복 (1, 2)
		            for (int div = 1; div <= 2; div++) {
		                WebElement link2 = driver.findElement(By.xpath("/html/body/main/article/div[2]/div/div/section/div[3]/ul/li[" + li + "]/div[" + div + "]/figure/a"));
		                String href = link2.getAttribute("href");

		                if (!uniqueHrefSet.contains(href)) {
		                    hrefList.add(href);
		                    uniqueHrefSet.add(href);
		                }
		            }
		        }

		        // hrefList 출력
		        for (String href : hrefList) {
		        	MangoInfoVO mvo=new MangoInfoVO();
		        	mvo.setMcno(vo.getMcno());
		            Document doc = Jsoup.connect(href).get();
		            
		            Elements name = doc.select("span.title h1.restaurant_name");
		            String namevalue = name.text();
		            
		            Element score=doc.selectFirst("strong.rate-point span");
		            String scorevalue= "";
		            double score1=0.0;
					if(score == null) {
						score1=0.0;
					} else {
						scorevalue = score.text();
					    score1 = Double.parseDouble(scorevalue);
					}
					
					Elements poster = doc.select("figure.restaurant-photos-item img.center-croping");
					String image = "";

					if (poster.isEmpty()) {
					    image = "https://innotalk.co.kr/images/error/no-image.png"; // 디폴트 이미지 링크
					} else {
					    for (int j = 0; j < poster.size(); j++) {
					        image += poster.get(j).attr("src") + "^";
					    }
					    image = image.substring(0, image.lastIndexOf("^"));
					    image = image.replace("&", "#");
					}

					
					String addr="no",phone="no",type="no",price="no",parking="no",time="no",menu="no";
					Elements etc=doc.select("table.info tr th");
					for(int a=0;a<etc.size();a++) {
						String ss=etc.get(a).text(); // th는 제목
						if(ss.equals("주소")) {
							Element e=doc.select("table.info tr td").get(a);
							addr=e.text();
						}else if(ss.equals("전화번호")) {
							Element e=doc.select("table.info tr td").get(a);
							phone=e.text();
						}else if(ss.equals("음식 종류")) {
							Element e=doc.select("table.info tr td").get(a);
							type=e.text();
						}else if(ss.equals("가격대")) {
							Element e=doc.select("table.info tr td").get(a);
							price=e.text();
						}else if(ss.equals("주차")) {
							Element e=doc.select("table.info tr td").get(a);
							parking=e.text();
						}else if(ss.equals("영업시간")) {
							Element e=doc.select("table.info tr td").get(a);
							time=e.text();
						}else if(ss.equals("메뉴")) {
							Element e=doc.select("table.info tr td").get(a);
							menu=e.text();
						}
					}
					
		            //System.out.println("Index: " + index + ", href: " + href);
					System.out.println("카테고리 번호:"+mvo.getMcno());
					System.out.println("업체명:"+name.text());
					System.out.println("평점:"+score1);
					System.out.println("이미지:"+image);
					System.out.println("주소:"+addr);
					System.out.println("전화:"+phone);
					System.out.println("음식 종류:"+type);
					System.out.println("가격대:"+price);
					System.out.println("주차:"+parking);
					System.out.println("영업시간:"+time);
					System.out.println("메뉴:"+menu);
					System.out.println("===========================================");
		            index++;
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		System.out.println("출력완료!!");
	}
	
	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 브라우저 창 숨기기
		DataCollection dc=new DataCollection();
		dc.detailData();

	}
}
