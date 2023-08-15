package com.encora.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.encora.test.util.DBConnector;

public class WelcomeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ProductDTO> productList = getProductList();
		String username = req.getParameter("username");
		PrintWriter pw = resp.getWriter();
		pw.print("<html>");
		pw.print("<center>");
		pw.print("<body bgcolor=\"#CCD1D1\">");
		pw.print("<h1>Welcome "+username+" current time is " + new Date() + "</h1>");
		
		pw.println("<table  border=\"2\">");
		pw.println("<tr><td>Name</td><td>Description</td><td>MRP price</td><tr>");
		
		for(ProductDTO productDTO : productList) {
			pw.println("<tr><td>"+productDTO.getProductName()+"</td><td>"+productDTO.getProductDescription()+"</td><td>"+productDTO.getUnitPrice()+"</td><td>");
		}
		
		pw.println("/<table>");
		pw.print("<html>");
		pw.print("</center>");
		pw.print("</body>");
		pw.print("<html>");
	}
	
	public List<ProductDTO> getProductList(){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		try {
		PreparedStatement ps = con.prepareStatement("select * from emp_db.product");
		ResultSet rs = ps.executeQuery();
		ProductDTO dto;
		
		while(rs.next()) {
			dto = new ProductDTO();
			dto.setProductId(rs.getLong(1));
			dto.setProductName(rs.getString(2));
			dto.setProductDescription(rs.getString(3));
			dto.setUnitPrice(rs.getDouble(4));
			productList.add(dto);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

}
