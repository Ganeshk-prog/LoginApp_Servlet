package com.encora.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DBConnector {
	public Connection getConnection() {
		Connection con = null;
		try {
			DriverManager.registerDriver(new Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_app","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return con;
		
	}

}
