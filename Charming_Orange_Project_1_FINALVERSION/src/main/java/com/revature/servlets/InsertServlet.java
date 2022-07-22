package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
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

public class InsertServlet extends HttpServlet{
	
	// http://localhost:8080/Charming_Orange_Project_1/insert
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StringBuilder uriString = new StringBuilder(req.getRequestURI()); // returns 
		

		uriString.replace(0, req.getContextPath().length() + 1, "");

		// if there is a slash
		if (uriString.indexOf("/") != -1) {
			uriString.replace(0, uriString.indexOf("/") + 1, "");

			PrintWriter writer = resp.getWriter();
			writer.write("Hello! :) Path variable: " + uriString.toString() + "\n");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		// http://localhost:8080/Charming_Orange_Project_1/insert?tablename='userinput'
		
		String insertType = req.getParameter("tablename");
		
		ObjectMapper mapper = new ObjectMapper();
		
		// ------------------------------------------------------------------------
		
		PrintWriter writer = resp.getWriter();
		
		//------------------------------
		
		Orm_Impl execute = new Orm_Impl();
		
		if(insertType == null) insertType = "";
		
		switch (insertType) {
		case "users":
			writer.write("Hello, " + insertType + "! :)");
			users testuser = mapper.readValue(req.getInputStream(), users.class);
			
			try {
				execute.InsertToTable(testuser);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			writer.write("\nINSERT SUCCESS!\n");
			
			String out = execute.printTable("users");
			
			writer.write(out);
			
			break;
			
		case "has_account":
			writer.write("Bonjour, " + insertType + "! :)");
			has_account testhas = mapper.readValue(req.getInputStream(), has_account.class);
			
			// -----------------------------------------------------
			
			ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
			
			try (Connection conn = connUtil.getConnection()) {
					
				String sql = "insert into has_account values (" + testhas.user_id + ", " + testhas.account_id + ")";
				
				System.out.println(sql);
				
				Statement st = conn.createStatement();
				st.executeUpdate(sql);
				
				writer.write("\nINSERT SUCCESS!\n");
				
				String out2 = execute.printTable("has_account");
				
				writer.write(out2);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			// ----------------------------------------------------------
			
			
			
			break;
			
		case "account":
			writer.write("Hola, " + insertType + "! :)");			
			account testaccount = mapper.readValue(req.getInputStream(), account.class);

			try {
				execute.InsertToTable(testaccount);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			writer.write("\nINSERT SUCCESS!\n");
			
			String out3 = execute.printTable("account");
			
			writer.write(out3);
			
			
			break;
		default:
			writer.write("aHhHhH wrong INPUT: " + insertType + "! :)");
		}
		
		// --------------------------------------------------------------------------
		
	}
	
	

}
