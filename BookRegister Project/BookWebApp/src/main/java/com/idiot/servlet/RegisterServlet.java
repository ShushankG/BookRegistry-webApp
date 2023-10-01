package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")

public class RegisterServlet extends HttpServlet  {
	
	private static final String query="INSERT INTO BOOKDATA(BOOK,EDITION,PRICE)VALUES(?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter out= resp.getWriter();
		//Setting content type
		
		resp.setContentType("text/html");
		
		//Getting book details
		
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
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, bookname);
			ps.setString(2, bookedition);
			ps.setFloat(3, bookprice);
			int count =ps.executeUpdate();
			if(count==1) {
				out.println("<h2>Record is Registered Successfully</h2>");
			}else {
				out.println("<h2>Record is not Registered</h2>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h2>"+e.getMessage()+"</h2>");
		}
		 out.println("<br>");
		    out.println("<a href='home.html'>Home</a>");
		    out.println("<br>");
		    out.println("<a href='list'>Book List</a>");

		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req,resp);
		
	}

}
