//GRANT ALL ON db.* TO root@'178.62.78.215' IDENTIFIED BY 'ukg5137grg';


package com.resham.shopping;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userName = request.getParameter("uname");
		String pwd = request.getParameter("pass");

		try {
			Class.forName("com.mysql.jdbc.Driver");

			//	Connection con = DriverManager.getConnection("jdbc:mysql://178.62.78.215/db", "root", "ukg5137grg");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "rooney");
			//Connection con = DriverManager.getConnection("jdbc:mysql://mysql-dev-01.cloud.wso2.com:3306/db_4technicaldiffi", "root", "rooney");
			
			Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from members where uname='"
					+ userName + "'and pass='" + pwd + "'");
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", userName);
				// out.println("welcome " + userid);
				// out.println("<a href='logout.jsp'>Log out</a>");
				response.sendRedirect("success.jsp");
			} else {
				response.sendRedirect("fail.jsp");
			//	System.out.println("FAIL BIG BIG FAILS");
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
