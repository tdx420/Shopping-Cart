<%@ include file="top.inc" %>

<%@ include file="middle.inc" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		} else if ( (session.getAttribute("userName").equals( "admin"))) { 
			
			response.sendRedirect("shop?action=viewAdmin");
		
	%>
	
	
	<%}else { %>
		<h3> <%=session.getAttribute("noItems")%></h3>
		
				 <ul id ="viewTotal">
				 
					<c:forEach items="${requestScope.cart}" var="cartItem">
			
				<li>				 
			   ${cartItem.value} x ${cartItem.key} 
				
					</c:forEach>

				</li>
			<h4>Total: £<%=session.getAttribute("total")%></h4>	

</ul>
	<a href="<c:url value="/shop"><c:param name="action" value="transaction" /></c:url>">Confirm Purchase</a>

	<%
		}
	%>
	
	
	



</body>
</html>


<%@ include file="bottom.inc" %>

	 











