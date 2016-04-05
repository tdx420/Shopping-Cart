package com.resham.shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;


public class Products {

  final  private static HashMap<Integer,Object> products = new HashMap<Integer, Object>();

  
    static {
    	
    	
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		

		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/db", "root", "rooney");
		Statement st = con.createStatement();
		ResultSet rs;
		rs = st.executeQuery("select * from food");
		
			
			for (int i =1; i<100; i++){
				
				if (rs.next()) {
					
					int id = rs.getInt("id");
					String name = rs.getString("name");
					double price =rs.getDouble("price");
				
				
				
				products.put(i , new Food(id, name , price));
				}
	
			
		}
    	
    	} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    //	Pizza a= new Pizza(1, "Double Pepperoni", 12.99);
    //	Pizza b= new Pizza(2, "Ham, pepperoni, beef & spicy pork", 13.99);
    //	Pizza c= new Pizza(3, "Chicken supreme", 13.99);
    	
     //   products.put(1,a);
     //   products.put(2,b);
     //   products.put(3,c);
        
        
      /*
        products.put(4,"Pineapple and ham");
        products.put(5,"Vegeterian Supreme");
        products.put(6,"Beef supreme");
        products.put(7,"Chicken, green peppers, fried onions");
        
        
        
    */

    }
    public static HashMap<Integer,Object> getAllProducts1() {
        return products;
    }

 
    


}