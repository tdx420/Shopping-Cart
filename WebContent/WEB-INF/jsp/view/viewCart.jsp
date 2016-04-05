<%@ include file="top.inc" %>

<%@ include file="middle.inc" %>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




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
	<br >
	<a href="index.jsp">Please Login</a>
	<%
		} else if ( (session.getAttribute("userName").equals( "admin"))) { 
			
			response.sendRedirect("shop?action=viewAdmin");
		
	%>
	
	
	<%}else { %>	  
	   	<h3>Basket</h3>

			    <% if(session.getAttribute("cart")  == null ){
			    %>
			    
			    
			    No items selected 
			    <%
			    } else {
			    %>
			<ul id ="viewCart">
				<c:forEach items="${requestScope.cart}" var="cartItem">
				
				<li>	
				 ${cartItem.value} x ${cartItem.key}  
				<a href="<c:url value="/shop">  
				</li>
				
    			
    			
    			<c:param name="action" value="deleteFromCart" />
    			<c:param name="cartItemId" value="${cartItem.value}"/>   
   				<c:param name="cartItemName" value="${cartItem.key}"/>   
    			</c:url>"  >
     			<img src="images/del.png" allign ="middle" height="23" width="23">
    			</a>
			   
				</c:forEach>
 				</ul>
				<br>	<br><br>	
			<a href="<c:url value="/shop"><c:param name="action" value="empty" /></c:url>">Empty Cart</a>
			 &nbsp; <a href="<c:url value="/shop"><c:param name="action" value="total" /></c:url>">Go To Checkout</a>
			<%
			
			    
			    }
			%>	

				
				  
	<%
		}
	%>
	
</body>
</html>

<%@ include file="bottom.inc" %>
