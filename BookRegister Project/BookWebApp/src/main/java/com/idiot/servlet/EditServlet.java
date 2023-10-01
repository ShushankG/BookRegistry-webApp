package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/editurl")
public class EditServlet extends HttpServlet {

private static final String query="update BOOKDATA set BOOK=?,EDITION=?,PRICE=? WHERE ID=?";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter out= resp.getWriter();
		//Setting content type
		
		resp.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("id"));
		
		//data to edit
		String bookname=req.getParameter("bookname");
		String bookedition=req.getParameter("bookedition");
		Float bookprice=Float.parseFloat(req.getParameter("bookprice"));
	
		//load jdbc driver
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Making connection with the database
		
		try {
			Connection con= DriverManager.getConnection("jdbc:mysql:///book","root","12345678");
			PreparedStatement ps=con.prepareStatement(query);
		    ps.setString(1,bookname);
		    ps.setString(2,bookedition);
		    ps.setFloat(3,bookprice);
		    ps.setInt(4, id);
			int count=ps.executeUpdate();
			if(count==1) {
				out.print("Update Successful !");
			}else {
				out.print("Update Failed !");
			}
			
		    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h2>" + e.getMessage() + "</h2>");
		}
		   out.println("<a href='home.html'>Home</a>");
		    out.println("<br>");
		    out.println("<a href='list'>Book List</a>");
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req,resp);
		
	}
	
}
