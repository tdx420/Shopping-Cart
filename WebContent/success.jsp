

   <link rel="stylesheet" type="text/css" href="images/style1.css" />

<%
    if ((session.getAttribute("userName") == null) || (session.getAttribute("userName") == "")) {
%>
You are not logged in, &nbsp;
<a href="index.jsp">Please Login</a>
<%} else {
%>
Welcome <%=session.getAttribute("userName")%>

 <% //response.sendRedirect("/WEB-INF/jsp/view/browse.jsp");
 	
 response.sendRedirect("shop?action=browse"); %>


 
<a href='logout.jsp'>Log out</a>
<%
    }
%>