package com.nanoboat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.nanoboat.bean.Customer;
import com.nanoboat.connection.DBConnection;

public class UserDao {

	public List getAllUsers() {

		Statement stmt = null;
		List allUsers = null;
		try {
			stmt = (Statement) DBConnection.getConnection().createStatement();
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			allUsers = new ArrayList<Customer>();
			Customer customer = null;
			while (rs.next()) {
				customer = new Customer();
				customer.setFirstName(rs.getString("first_name"));
				allUsers.add(customer);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		}
		return allUsers;
	}
	
	public void editUser(){
		
	}

	public static void main(String[] args) {

		//getAllUsers();

	}
}