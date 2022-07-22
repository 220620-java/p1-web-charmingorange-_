package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.orm.Orm_Impl;
import com.revature.webAppClasses.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// query/request parameters
		// http://localhost:8080/Charming_Orange_Project_1/update?tablename='user input'
		
		String tableName = req.getParameter("tablename");
		
		String idToUpdate = req.getParameter("id");
		
		ObjectMapper mapper = new ObjectMapper();
		
		PrintWriter writer = resp.getWriter();
		
		//------------------------------
		
		Orm_Impl execute = new Orm_Impl();
		
		if(tableName == null) tableName = "";
		
		switch (tableName) {
		case "users":
			writer.write("Hello, " + tableName + "! :)");
			users user = mapper.readValue(req.getInputStream(), users.class);
			
			try {
				execute.UpdateOBJ(user, idToUpdate);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			writer.write("\nUPDATE SUCCESS!\n");
			
			String out = execute.printTable("users");
			
			writer.write(out);
				
			break;
			
		case "has_account":
			writer.write("Bonjour, " + tableName + "! :)");
			has_account hasA = mapper.readValue(req.getInputStream(), has_account.class);
			
			try {
				execute.UpdateOBJ(hasA, idToUpdate);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			writer.write("\nUPDATE SUCCESS!\n");
			
			String out2 = execute.printTable("has_account");
			
			writer.write(out2);
			
			break;
		case "account":
			writer.write("Hola, " + tableName + "! :)");
			account acc = mapper.readValue(req.getInputStream(), account.class);
			
			try {
				execute.UpdateOBJ(acc, idToUpdate);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			writer.write("\nUPDATE SUCCESS!\n");
			
			String out3 = execute.printTable("account");
			
			writer.write(out3);
			
			
			break;
		default:
			writer.write("aHhHhH wrong INPUT: " + tableName + "! :)");
		}
		
		
		//-------------------------------
		
		
	}	

}
