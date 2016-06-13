package com.nanoboat.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.nanoboat.util.NanoboatConstant;

public class DBConnection {
	static Connection conn = null;
	static Statement stmt = null;
	static Logger logger = Logger.getLogger(DBConnection.class.getName());

	public static Connection getConnection() {
		try {
			Class.forName(NanoboatConstant.JDBC_DRIVER);
			conn = DriverManager.getConnection(NanoboatConstant.DB_URL, NanoboatConstant.USER, NanoboatConstant.PASS);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.info("connection created " + conn);
		return conn;
	}

	public static void insertData(String sql) throws ClassNotFoundException {
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ResultSet selectData(String sql) throws ClassNotFoundException {

		ResultSet resultSet = null;
		try {
			stmt = getConnection().createStatement();
			resultSet = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		getConnection();
		System.out.println("connected");
	}
}