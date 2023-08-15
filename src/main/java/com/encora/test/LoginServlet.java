package com.encora.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.encora.test.util.DBConnector;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("pass");
		boolean result = validateUser(username, password);
		if(result) {
//			resp.sendRedirect("/welcome.php");
			RequestDispatcher rd = req.getRequestDispatcher("/welcome.php");
			rd.forward(req,resp);
			}else {
				resp.sendRedirect("index.html");
			}
		
	}
	private boolean validateUser(String userName, String password) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from login_info where username=? and password=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
