package com.sist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sist.dao.*;
import com.sist.vo.*;

public class MangoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String url="jdbc:oracle:thin:@211.238.142.121:1521:XE";
	private static MangoDAO dao;
	
	public MangoDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url,"hr","happy");

		}catch(Exception e) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
		
	}
	public static MangoDAO newInstance() {
		if(dao==null)
			dao=new MangoDAO();
		return dao;
	}
	
	public List<CategoryVO> CategoryData(){
		List<CategoryVO> list=new ArrayList<CategoryVO>();
		try {
			getConnection();
			String sql="SELECT mcno,title,link "
					+ "FROM mango_category";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CategoryVO vo=new CategoryVO();
				vo.setMcno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setLink(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	public void MangoInfoData(MangoInfoVO vo) {
		try {
			getConnection();

			String sql="INSERT INTO food_info "
					+ "VALUES(fi_fino_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
	        ps.setInt(1, vo.getMcno());
	        ps.setString(2, vo.getPoster());
	        ps.setString(3, vo.getName());
	        ps.setDouble(4, vo.getScore());
	        ps.setString(5, vo.getAddress());
	        ps.setString(6, vo.getPhone());
	        ps.setString(7, vo.getType());
	        ps.setString(8, vo.getPrice());
	        ps.setString(9, vo.getParking());
	        ps.setString(10, vo.getTime());
	        ps.setString(11, vo.getMenu());
	        ps.setInt(12, vo.getHit());
	        ps.setInt(13, vo.getLike_count());
			
			//실행요청!!
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
}
