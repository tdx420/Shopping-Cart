<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html> 
 
<head> 
  <title>Resham Firiri TakeAway</title> 
</head> 
 
<body style="margin:auto; padding: 0"> 
 
<table width="728" 
           cellspacing="0" 
           cellpadding="0"
           style="padding: 0; margin: auto; border-collapse: collapse;"> 
<tr style="padding: 0; margin: auto"> 
<td width="728" 
       colspan="2" 
       style="padding: 0; margin: auto"><img 
          src="logo.jpg" 
          style="padding: 0; margin"></td> 
</tr> 
<tr style="padding: 0; margin: auto"> 
<td width="728"  
       colspan="2" 
       style= " padding: 0; margin: auto"><div 
         style=" height: 19px;padding: 0; 
                    margin: auto; 
                  
                    font-size: small; 
                    background-color: #99CCCC"> 
      <div id="div1"> Resham Firiri TakeAway </div>
</div></td> 
</tr> 
<tr> 
    <td rowspan="2" 
           width="240"
           valign="top"> 
       <div style="height: 150px; padding: 20px; border-right: 3px dashed; margin-right: 20px"> 
 		  Welcome
			<%=session.getAttribute("userName")%><br/><br/>
		  <a href="<c:url value="/shop"><c:param name="action" value="browse" /></c:url>">Menu</a>	<br/>
          <a href="<c:url value="/shop"><c:param name="action" value="viewCart" /></c:url>"> Basket </a> <br/>
          <a href="<c:url value="/shop"><c:param name="action" value="history" /></c:url>">History</a>	<br/>
		  <a href='logout.jsp'>Log out</a>
          
          
       </div> 
    </td> 
    <td width="488"> 
         <div style="margin-top: 12px; margin-bottom: 12 px"> 