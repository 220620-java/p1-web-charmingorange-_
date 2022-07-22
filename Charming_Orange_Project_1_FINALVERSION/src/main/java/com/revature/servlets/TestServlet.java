package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.revature.orm.*;
import com.revature.pepinUtil.*;
import com.revature.webAppClasses.*;
import com.revature.testOrm.*;

public class TestServlet extends HttpServlet {

	
	// http://localhost:8080/Charming_Orange_Project_1/getTable
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StringBuilder uriString = new StringBuilder(req.getRequestURI()); // returns 
																			

		uriString.replace(0, req.getContextPath().length() + 1, "");

		// if there is a slash
		if (uriString.indexOf("/") != -1) {
			uriString.replace(0, uriString.indexOf("/") + 1, "");

			PrintWriter writer = resp.getWriter();
			writer.write("Hello! :) Path variable: " + uriString.toString() + "\n");
			
			// ---------------
			
			Orm_Impl execute = new Orm_Impl();
			
			String[] theBigTest = execute.getAllColumns("users");
			
			String woW = "";
			
			for(String s : theBigTest) {
				woW = woW.concat(s).concat("\n");
			}
			
			PrintWriter woowoo = resp.getWriter();
			woowoo.write(woW);
			
			// ---------------
			
			// execute.InsertToTable(uriString)
			
			String testing = execute.printTable("users");
			
			PrintWriter please = resp.getWriter();
			please.write(testing);
			
			
		} else {

			// gets the response if there is no slash (normal hello)

			// gets the response body writer object so that we can write to the response
			// body

			PrintWriter writer = resp.getWriter();
			writer.write("Hello! :)");

		}

	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// query/request parameters
		// http://localhost:8080/Charming_Orange_Project_1/getTable?tablename='user input'
		
		String tableName = req.getParameter("tablename");
		
		PrintWriter writer = resp.getWriter();
		
		//------------------------------
		
		Orm_Impl execute = new Orm_Impl();
		
		
		if(tableName == null) tableName = "";
		
		switch (tableName) {
		case "users":
			writer.write("Hello, " + tableName + "! :)");
			
			String testing = execute.printTable("users");
			PrintWriter please = resp.getWriter();
			please.write(testing);
			
			break;
			
		case "has_account":
			writer.write("Bonjour, " + tableName + "! :)");
			
			String testing2 = execute.printTable("has_account");
			PrintWriter please2 = resp.getWriter();
			please2.write(testing2);
			
			break;
		case "account":
			writer.write("Hola, " + tableName + "! :)");
			
			String testing3 = execute.printTable("account");
			PrintWriter please3 = resp.getWriter();
			please3.write(testing3);
			
			break;
		default:
			writer.write("aHhHhH wrong INPUT: " + tableName + "! :)");
		}
		
		
		//-------------------------------
		
		
		
	}

	
	
	
}
