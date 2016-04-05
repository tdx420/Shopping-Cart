<%@ include file="top_admin.inc" %>

<%@ include file="middle.inc" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Date"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="images/style1.css" />
</head>
<body>



	<h3>Admin Panel</h3>

	



	<%
		String userName = (String) session.getAttribute("userName");

			try {
				Class.forName("com.mysql.jdbc.Driver");

			} catch (ClassNotFoundException e) {
				System.out.println("Class not found " + e);
			}
			try {
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost/db", "root", "rooney");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery
				//   ("SELECT * FROM history WHERE user_name = 'tdx'   ");

						//		 ("SELECT * FROM history WHERE user_name = '"+userName+"' ");

						("SELECT * FROM history ORDER BY (purchase_date) desc  ");
				//System.out.println(rs.getRow() + "row ");

				if (!rs.isBeforeFirst()) { //res.isBeforeFirst() is true if the cursor
					//is before the first row.  If res contains
					//no rows, rs.isBeforeFirst() is false.

					out.write("No order recieved yet");
					
					
					
					System.out.println("0 rows");
				} else {
					out.write("<ul id=viewAdmin>");
					while (rs.next()) {
						//int id = rs.getInt("id");

						String user_name = rs.getString("user_name");
						String product_history = rs
								.getString("product_history");
						Date purchase_date = rs.getTimestamp("purchase_date");
						//Timestamp timestamp = new Timestamp(purchase_date.getTime());
						double total_expense = rs.getDouble("total_expense");
						
						
					
						
						out.write( "<li> '" + "User: " + user_name +  "<br />"+ " Date: " + purchase_date
								+ "<br />" + product_history + "<br />"
								+ "Total Expenditure: " + total_expense
								+ "'   <br /> ");
						out.write("</li>" + "</br>");
						
					}
					out.write("</ul>");
				
				}

			
				
				
				
				

			} catch (SQLException e) {
				System.out.println("SQL exception occured" + e);
			}
	%>



	

</body>
</html>

<%@ include file="bottom.inc" %>
