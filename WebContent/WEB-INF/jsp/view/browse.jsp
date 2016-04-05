<%@ include file="top.inc" %>

<%@ include file="middle.inc" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
 <link rel="stylesheet" type="text/css" href="images/style1.css" />

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script type='text/javascript' src="images/codex-fly.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$('.add-to-cart1').on('click',function(){
				//Scroll to top if cart icon is hidden on top
				$('html, body').animate({
					'scrollTop' : $(".cart_anchor").position().top
				});
				//Select item image and pass to the function
				var itemImg = $(this).parent().find('img').eq(0);
				flyToElement($(itemImg), $('.cart_anchor'));
			});
		});
		</script>
		
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
	
	
	<%if(session.getAttribute("total") == null){ %>

		<span><i class="cart_anchor"></i></span>

    <%}else{ %>
    		<i class="cart_anchor">
	   <h10> £<%=session.getAttribute("total") %></h10></i>
    <%    } %>	
	    	
	<h3>Menu</h3>
	
	<h9>Food</h9> <br>
	>
	<c:forEach items="${requestScope.products}" var="product"  >
	
				<ul class ="items" id= mainMenu >	
		 		<li>	
				<a  class="btn btn-success add-to-cart1"
		
		
					href="<c:url value="/shop">
		        <c:param name="action" value="addToCart"/>
		        <c:param name="productId" value="${product.key}"/>
		       </c:url>"  >
		 		
		 
		 		<table  border="0" width="100%" >
				<tr >
		 		<td width="65%"> ${product.value.name}       </td>
				<td width="25%"> £${product.value.price} 	  </td>
				<td width="10%"> <img height =40 width =50 src="images/item1.jpg" >    </td> 	

				</tr>	
				</table>
				
				</a>
				</li>
				</ul>
				
	
	</c:forEach>
	

	<br><h9>Sides</h9> <br>

	<c:forEach items="${requestScope.sideProducts}" var="sideProduct">
	   
	<div class="item">
		    <a    class="add-to-cart"  href="
				    <c:url value="/shop">
			        <c:param name="action" value="addSideProductsToCart"/>
			        <c:param name="sideProductId" value="${sideProduct.key}"/>
					
			        </c:url>"  >	 
		 
	        <ul  id= "sideMenu"  >
	   		
	 		<li>     			
	 		<table  border="0" width="100%" >
			<tr >
					
			
			<td id="tmp_name" width="65%"> ${sideProduct.value.name}     </td>
			<td width="25%">£${sideProduct.value.price}  </td>
			<td  width="10%">    <img height =40 width =50 src="images/item1.jpg" alt="some_text">  </td> 		
			
					
			</tr>	
			</table>	
			</li>
			
			</ul>
			</a> 

	</div>
	
	
	</c:forEach>

	
	
		<br><h9>Dessert</h9> <br>

		<c:forEach items="${requestScope.dessertProducts}" var="dessertProduct">
			   <ul class="items" id ="dessertMenu">
	 		   <li>
				
				<a class="add-to-cart1"  
					href="<c:url value="/shop">
		        <c:param name="action" value="addDessertProductsToCart"/>
		        <c:param name="dessertProductId" value="${dessertProduct.key}"/>
		        </c:url>">
		        
				      	
					 			<table  border="0" width="100%" >
									<tr >
										<td id="tmp_name" width="65%"> ${dessertProduct.value.name}     </td>
										<td width="25%">£${dessertProduct.value.price} </td>
										<td  width="10%">     <img  height =40 width =50 src="images/item1.jpg" alt="some_text">   </td> 
															
										     
									</tr>	
								</table>
						
				</a>
		
				</li>
				</ul>
		</c:forEach>

	
			<% if( (session.getAttribute("addToCartMessage") == null)  ){ %>
				<h4> </h4>
			<%}else{ %>
			

		  <h5> <%=session.getAttribute("addToCartMessage")%></h5> <br/>
		
				
			<%} %>
		
	<br>
	<br>
	<br>
	

		
 
  <script src="images/prefixfree.min.js"></script>
  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
  <script src="images/index.js"></script>







	<%
		}
	%>
	    	
	    	
	    	
	    	

	

</body>
</html>

<%@ include file="bottom.inc" %>

