package com.sist.vo;
/*
 이름                                      널?      유형
 ----------------------------------------- -------- ----------------------------
 FINO                                      NOT NULL NUMBER
 MCNO                                               NUMBER
 POSTER                                    NOT NULL VARCHAR2(4000)
 NAME                                      NOT NULL VARCHAR2(200)
 SCORE                                     NOT NULL NUMBER(2,1)
 ADDRESS                                   NOT NULL VARCHAR2(1000)
 PHONE                                     NOT NULL VARCHAR2(20)
 TYPE                                      NOT NULL VARCHAR2(100)
 PRICE                                              VARCHAR2(100)
 TIME                                               VARCHAR2(100)
 MENU                                               VARCHAR2(4000)
 GOOD                                               NUMBER
 SOSO                                               NUMBER
 BAD                                                NUMBER
 JJIM_COUNT                                         NUMBER
 LIKE_COUNT                                         NUMBER
 RDAY                                               VARCHAR2(100)
 */
public class MangoInfoVO {
	 private int fino,mcno,good,soso,bad,jjim_count,like_count;
	 private double score;
	 private String poster,name,address,phone,type,price,time,menu,rday;
	public int getFino() {
		return fino;
	}
	public void setFino(int fino) {
		this.fino = fino;
	}
	public int getMcno() {
		return mcno;
	}
	public void setMcno(int mcno) {
		this.mcno = mcno;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getSoso() {
		return soso;
	}
	public void setSoso(int soso) {
		this.soso = soso;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getJjim_count() {
		return jjim_count;
	}
	public void setJjim_count(int jjim_count) {
		this.jjim_count = jjim_count;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getRday() {
		return rday;
	}
	public void setRday(String rday) {
		this.rday = rday;
	}
	 
	
}
