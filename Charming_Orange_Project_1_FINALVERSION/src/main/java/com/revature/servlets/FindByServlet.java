package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.orm.*;
import com.revature.pepinUtil.*;
import com.revature.webAppClasses.*;
import com.revature.testOrm.*;

public class FindByServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// query/request parameters
		// http://localhost:8080/Charming_Orange_Project_1/getTable?tablename='user input'
		
		String tableName = req.getParameter("tablename");
		
		String idToFind = req.getParameter("id");
		
		ObjectMapper mapper = new ObjectMapper();
		
		PrintWriter writer = resp.getWriter();
		
		//------------------------------
		
		ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
		
		//Orm_Impl execute = new Orm_Impl();
		
		if(tableName == null) tableName = "";
		if(idToFind == null) idToFind = "";
		
		switch(tableName) {
		case "users":

			try (Connection conn = connUtil.getConnection()) {
				
				String sql = "select * from users where user_id = '" + idToFind + "'";
				
				System.out.println(sql);
				
				Statement st = conn.createStatement();
				ResultSet woo = st.executeQuery(sql);
				
				String outPut = "";
				
				while(woo.next()) {
					
					String u1 = "user_id: " + woo.getString(1) + "\n";
					String u2 = "user_name: " + woo.getString(2) + "\n";
					String u3 = "user_pass: " + woo.getString(3) + "\n";
					
					outPut = u1 + u2 + u3;
					
				}
				
				if(outPut.equals("")) {
					writer.write("Record Not Found...  :(");
				}
				else {
					writer.write(outPut);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		case "has_account":
			
			try (Connection conn = connUtil.getConnection()) {
				
				String sql = "select * from has_account where user_id = '" + idToFind + "'";
				
				//System.out.println(sql);
				
				Statement st = conn.createStatement();
				ResultSet woo = st.executeQuery(sql);
				
				String outPut = "";
				
				while(woo.next()) {
					
					String u1 = "user_id: " + woo.getString(1) + "\n";
					String u2 = "account_id: " + woo.getString(2) + "\n";
					
					outPut = u1 + u2;
					writer.write(outPut);
					
					writer.write("\n");
					
				}
				
				if(outPut.equals("")) {
					writer.write("Record Not Found...  :(");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			break;
			
		case "account":

			try (Connection conn = connUtil.getConnection()) {
				
				String sql = "select * from account where account_id = '" + idToFind + "'";
		
				Statement st = conn.createStatement();
				ResultSet woo = st.executeQuery(sql);
				
				String outPut = "";
				
				while(woo.next()) {
					
					String u1 = "account_id: " + woo.getString(1) + "\n";
					String u2 = "account_name: " + woo.getString(2) + "\n";
					String u3 = "account_balance: " + woo.getString(3) + "\n";
					
					outPut = u1 + u2 + u3;
					writer.write(outPut);
					
					writer.write("\n");
					
				}
				
				if(outPut.equals("")) {
					writer.write("Record Not Found...  :(");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
			
		default:
			writer.write("ERROR: TABLE NOT FOUND");
		}
		
		
	}
	
	

}
