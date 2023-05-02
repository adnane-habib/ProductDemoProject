package com.product;



/**
 * Servlet implementation class ProductListServlet
 */


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;

import java.util.Properties;

/**
 * Servlet implementation class ProductListServlet
 */
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		//Step 2:
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
		Properties props = new Properties();
		props.load(in);
		
		String url = props.getProperty("url");
		String userid = props.getProperty("userid");
		String password = props.getProperty("password");
		
		DBConnection dbConnection = null;
		
		try {
			dbConnection = new DBConnection(url, userid, password);
			
			
			Connection connection = dbConnection.getConnection();
			
			// STEP3: Create Statement Object
			
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("select productCode, productName, productLine, productDescription from products");
			out.println("PRODUCT LIST<br><br>");
			out.println("<table border=1><th>ID<th>NAME<th>LINE<th>DESCRIPTION</th>");
			
			
			while(rs.next()) {
				String ID = rs.getString("productCode");
				String name = rs.getString("productName");
				String line = rs.getString("productLine");
				String productDescription = rs.getString("productDescription");
				
				out.println("<tr><td>"+ID + "<td>"+name + "<td>"+line + "<td>" + productDescription+ "</tr>");
			}
			out.println("</table>");
			
			
			
			
			
		}catch (Exception e) {
			
			out.println(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
