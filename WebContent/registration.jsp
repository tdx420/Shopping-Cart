<%@ page import ="java.sql.*" %>
<%
    String user = request.getParameter("uname");    
    String pwd = request.getParameter("pass");
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String email = request.getParameter("email");
    Class.forName("com.mysql.jdbc.Driver");
    //   Connection con = DriverManager.getConnection("jdbc:mysql://178.62.78.215:3306/db", "root", "ukg5137grg");
 //    Connection con = DriverManager.getConnection("jdbc:mysql://mysql-dev-01.cloud.wso2.com:3306/db_4technicaldiffi", "root", "rooney");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "rooney");
    Statement st = con.createStatement();
    //ResultSet rs;
    int i = st.executeUpdate("insert into members(first_name, last_name, email, uname, pass, regdate) values ('" + fname + "','" + lname + "','" + email + "','" + user + "','" + pwd + "', CURDATE())");
    if (i > 0) {
        //session.setAttribute("userid", user);
        response.sendRedirect("welcome.jsp");
       // out.print("Registration Successfull!"+"<a href='index.jsp'>Go to Login</a>");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
