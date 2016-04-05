package com.resham.shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SideProducts {

	final private static HashMap<Integer, Object> sideProducts = new HashMap<Integer, Object>();

	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/db", "root", "rooney");
			Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from sidemenu");

			for (int i = 1; i < 100; i++) {

				if (rs.next()) {

					// int id = rs.getInt("id");
					String name = rs.getString("name");
					double price = rs.getDouble("price");

					sideProducts.put(i, new SideMenu(name, price));
				}

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * 
		 * SideMenu a = new SideMenu("Coke", 0.99); SideMenu b = new
		 * SideMenu("Pepsi", 0.99); SideMenu c = new SideMenu("Water", 0.50);
		 * 
		 * sideProducts.put(1,a); sideProducts.put(2,b); sideProducts.put(3,c);
		 */

	}

	public static HashMap<Integer, Object> getAllSideProducts() {
		return sideProducts;
	}

}