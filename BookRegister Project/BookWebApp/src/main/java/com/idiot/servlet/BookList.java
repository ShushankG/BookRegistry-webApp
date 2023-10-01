package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/list")
public class BookList extends HttpServlet {
	private static final String query="SELECT* FROM BOOKDATA";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter out= resp.getWriter();
		//Setting content type
		
		resp.setContentType("text/html");
	
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
			ResultSet rs=ps.executeQuery();
			out.println("<table border='1' align='center'>");
			out.println("<tr>");
			out.println("<th> ID</th>");
			out.println("<th> Name</th>");
			out.println("<th> Edition</th>");
			out.println("<th> Price</th>");
			out.println("<th> Edit</th>");
			out.println("<th> Delete</th>");
			out.println("</tr>");
			while(rs.next()){
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getFloat(4)+"</td>");
				out.println("<td> <a href='editScreen?id="+rs.getInt(1)+"'><span style='color:green;'>Edit</span></a></td>");
				out.println("<td> <a href='deleteurl?id="+rs.getInt(1)+"'><span style='color:red;'>Delete</span></a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
		    out.println("<br>");
		    out.println("<a href='home.html'>Home</a>");
		    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h2>"+e.getMessage()+"</h2>");
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req,resp);
		
	}
}
