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
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
private static final String query="SELECT BOOK, EDITION,PRICE FROM BOOKDATA WHERE ID= ?";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter out= resp.getWriter();
		//Setting content type
		
		resp.setContentType("text/html");
		
		int id=Integer.parseInt(req.getParameter("id"));
	
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
		    ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			//out.println("<form action='editurl?id="+id+"',method='post'>");
			out.println("<form action='editurl?id=" + id + "' method='post'>");

			out.println("<table align='center'>");
			out.println("<tr>");
			out.println("<td>Book Name :</td>");
			out.println("<td><input type='text' name='bookname' value="+rs.getString(1)+"></td>");
			out.println("</tr");
			
			out.println("<tr>");
			out.println("<td>Book Edition :</td>");
			out.println("<td><input type='text' name='bookedition' value="+rs.getString(2)+"></td>");
			out.println("</tr");
			
			out.println("<tr>");
			out.println("<td>Book Price :</td>");
			out.println("<td><input type='text' name='bookprice' value="+rs.getString(3)+"></td>");
			out.println("</tr");
			
			out.println("<tr>");
			out.println("<td><input type='submit' value='Edit'></td>");
			out.println("<td><input type='reset' value='Cancel'></td>");
			out.println("</tr");
			
			out.println("</table>");
			out.println("</form");
		    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h2>hi</h2>");
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req,resp);
		
	}
}
