<%@ include file="top.inc" %>

<%@ include file="middle.inc" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
 <link rel="stylesheet" type="text/css" href="images/style1.css" />
</head>
<body>

	<%
		if ((session.getAttribute("userName") == null)
				|| (session.getAttribute("userName") == "")) {
			response.sendRedirect("index.jsp");
	%>
	You are not logged in
	<br />
	<a href="index.jsp">Please Login</a>
	<%
		} else {
	%>
			
			
	
				<h3>Transaction  </h3>
		
			Thank you , <%=session.getAttribute("userName")%> . Your order has been confirmed. 
	
			

						
				  
	<%
		}
	%>
	
</body>
</html>

<%@ include file="bottom.inc" %>
