package com.resham.shopping;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**

 */

@WebServlet(name = "shoppingServlet", urlPatterns = "/shop")
public class ShoppingServlet extends HttpServlet {

	private Map<Integer, Object> products = new HashMap<>();
	private Map<Integer, Object> sideProducts = new HashMap<>();
	private Map<Integer, Object> dessertProducts = new HashMap<>();

	@Override
	public void init() throws ServletException {
		products = Products.getAllProducts1();
		sideProducts = SideProducts.getAllSideProducts();
		dessertProducts = DessertProducts.getAllDessertProducts();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		if (action == null)
			action = "browse";
	

		switch (action) {
		case "addToCart":
			this.addToCart(req, resp);
			break;
		case "addSideProductsToCart":
			this.addSideProductsToCart(req, resp);
			break;
		case "addDessertProductsToCart":
			this.addDessertProductsToCart(req, resp);
			break;	
		case "viewCart":
			this.viewCart(req, resp);
			break;
		case "empty":
			this.empty(req, resp);
			break;
		case "browse":
			this.browse(req, resp);
			break;
		case "deleteFromCart":
			this.deleteFromCart(req, resp);
			break;
		case "total":
			this.total(req, resp);
			break;
		case "viewTotal":
			this.viewTotal(req, resp);
			break;
		case "transaction":
			try {
				this.transaction(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "history":
			this.history(req, resp);
			break;
			
		case "viewAdmin":
			this.viewAdmin(req, resp);
			break;	

		}
	}

	

	private void viewAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/jsp/view/viewAdmin.jsp").forward(req,
				resp);
	}

	private void empty(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().removeAttribute("cart");
		req.getSession().removeAttribute("total");
		viewCart(req, resp);
	}

	@SuppressWarnings("unchecked")
	private void viewCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<Object, Integer> cart = (Map<Object, Integer>) req.getSession()
				.getAttribute("cart");

		req.setAttribute("cart", cart);
		req.getSession().removeAttribute("addToCartMessage");
		req.getRequestDispatcher("/WEB-INF/jsp/view/viewCart.jsp").forward(req,
				resp);
	}

	@SuppressWarnings("unchecked")
	private void addToCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer productId = Integer.parseInt(req.getParameter("productId")); // product
																				// key

		HttpSession session = req.getSession();
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new HashMap<Object, Integer>()); // cart
																			// string
																			// and
																			// integer
		}
		Map<Object, Integer> cart = (Map<Object, Integer>) session
				.getAttribute("cart");

		if (cart.containsKey(products.get(productId))) {
			
			cart.put(products.get(productId),
					cart.get(products.get(productId)) + 1);
			
/*
			  ScriptEngineManager engineManager = new ScriptEngineManager();
			   
			          // Create ScriptEngine
			          ScriptEngine engine = engineManager.getEngineByName("ECMAScript");
			   
			          //Create file and reader instance for reading the script file
			          File file = new File("images/fly.js");
			          Reader reader = new FileReader(file);
			   
			          //Pass the script file to the engine
			          try {
						engine.eval(reader);
					} catch (ScriptException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			  //        System.out.println("Java Program Output");
			          //Create invocable instance
			          Invocable invocable = (Invocable) engine;
			   
	
			          //Invoke the methods defined in the script file
		
			          try {
						invocable.invokeFunction("fly", "images/fly.js");
					} catch (NoSuchMethodException | ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			*/
			

		} else
			cart.put(products.get(productId), 1);

		// String prevMess = (String) session.getAttribute("addToCartMessage");
		String addToCartMessage = products.get(productId)
				+ " added to the cart";
		session.setAttribute("addToCartMessage", addToCartMessage);
		// session.setAttribute("addToCartMessagePrev", prevMess);

		/*
		 * <% if((session.getAttribute("addToCartMessagePrev") == null)){ %>
		 * <h4> </h4><%}else{ %> Previous: <h3>
		 * <%=session.getAttribute("addToCartMessagePrev")%></h3> <%} %>
		 */
		totaltem(req);
		resp.sendRedirect("shop?action=browse");

	}

	public void addSideProductsToCart(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		

		HttpSession session = req.getSession();
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new HashMap<Object, Integer>());
		}

		Map<Object, Integer> cart = (Map<Object, Integer>) session
				.getAttribute("cart");

		
			
		Integer sideProductId = Integer.parseInt(req
				.getParameter("sideProductId"));
		
		

					if (cart.containsKey(sideProducts.get(sideProductId))) {
						cart.put(sideProducts.get(sideProductId),
								cart.get(sideProducts.get(sideProductId)) + 1);
						
					} else
						cart.put(sideProducts.get(sideProductId), 1);
					
					
					String addToCartMessage = sideProducts.get(sideProductId)
							+ " added to the cart";
					session.setAttribute("addToCartMessage", addToCartMessage);
					
					session.setAttribute("addToCartMessage1", "TRUE");
					
			
					totaltem(req);
			//	totaltem(req);
					
					// System.out.println( sideProductId ); // returns the key 1
					// System.out.println(sideProducts.get( sideProductId ) ); //string
					// returns the String value to which the key is specified coke
					// System.out.println( cart.get(sideProducts.get( sideProductId)) );
					// //returns the value of cart
					// session.setAttribute("cart", cart);
					resp.sendRedirect("shop?action=browse");

		
	
	}

	
	private void addDessertProductsToCart(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		
		
		
		
		Integer dessertProductId = Integer.parseInt(req
				.getParameter("dessertProductId"));

		HttpSession session = req.getSession();
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new HashMap<Object, Integer>());
		}

		Map<Object, Integer> cart = (Map<Object, Integer>) session
				.getAttribute("cart");

		if (cart.containsKey(dessertProducts.get( dessertProductId))) {
			cart.put(dessertProducts.get( dessertProductId),
					cart.get(dessertProducts.get( dessertProductId)) + 1);
		} else
			cart.put(dessertProducts.get( dessertProductId), 1);

		String addToCartMessage = dessertProducts.get( dessertProductId)
				+ " added to the cart";
		session.setAttribute("addToCartMessage", addToCartMessage);
		// System.out.println( sideProductId ); // returns the key 1
		// System.out.println(sideProducts.get( sideProductId ) ); //string
		// returns the String value to which the key is specified coke
		// System.out.println( cart.get(sideProducts.get( sideProductId)) );
		// //returns the value of cart
		// session.setAttribute("cart", cart);
		totaltem(req);
		resp.sendRedirect("shop?action=browse");
		
		
		
		
	}
	private void deleteFromCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Integer cartItemId = Integer.parseInt(req.getParameter("cartItemId")); // value
		Object cartItemName = req.getParameter("cartItemName");// key
		HttpSession session = req.getSession();

		Map<Object, Integer> carts = (Map<Object, Integer>) session
				.getAttribute("cart");
		Object productName;
		Integer totalNoOfProduct;

		Iterator it = carts.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();

			productName = entry.getKey();
			totalNoOfProduct = (Integer) entry.getValue();

			if (productName.toString().equals(cartItemName.toString())) {
				if (totalNoOfProduct <= 1) {
					it.remove();
					req.setAttribute("cart", carts);

				} else
					carts.put(productName, totalNoOfProduct - 1);
			}

		}
		totaltem(req);
		
		resp.sendRedirect("shop?action=viewCart");
	}

	private void browse(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("products", products);
		req.setAttribute("sideProducts", sideProducts);
		req.setAttribute("dessertProducts", dessertProducts);
		//this.total(req, resp);
		// req.setAttribute("addToCartMessage", null);
		req.getRequestDispatcher("/WEB-INF/jsp/view/browse.jsp").forward(req,
				resp);
	}
	
	private void totaltem(HttpServletRequest req){
		HttpSession session = req.getSession();
		Map<Object, Integer> carts = (Map<Object, Integer>) session
				.getAttribute("cart");
		Map<Integer, Object> products = (Map<Integer, Object>) Products
				.getAllProducts1();
		Map<Integer, Object> sideProducts = (Map<Integer, Object>) SideProducts
				.getAllSideProducts();
		
		Map<Integer, Object> dessertProducts = (Map<Integer, Object>) DessertProducts
				.getAllDessertProducts();
		
		String productHistory = "";
		// System.out.println(carts.values());
		Object productName = "";
		Integer totalNoOfProduct = 0;
		Double total = 0.0;
		session.setAttribute("total", total);
		// htt?.,m
		// n,p://stackoverflow.com/questions/5826384/java-iteration-through-a-hashmap-which-is-more-efficient

		if (carts == null) {
			session.setAttribute("total", 0.0);
			session.setAttribute("noItems", "You have not selected any items");
		} else

			for (Map.Entry<Object, Integer> entry : carts.entrySet()) {
				session.setAttribute("noItems", "Checkout");
				productName = entry.getKey();
				totalNoOfProduct = entry.getValue();

				Iterator<Object> pizza = products.values().iterator();
				while (pizza.hasNext()) {

					Food tmp = (Food) pizza.next();
					Double price = (double) tmp.getPrice();
					String name = tmp.getName();

					if (productName.toString().equals(name)) {
						
					//	session.setAttribute("foodPrice", price);
						
						total = total + (price * totalNoOfProduct);
						// System.out.println("TOTAL" + total);
					 session.setAttribute("total",	new DecimalFormat("##.##").format(total));
			
					
					 
					//	productHistory += totalNoOfProduct + "x" + productName + "@ " + price + " || ";
						productHistory =productHistory +( totalNoOfProduct + "x" + productName + "@ " + price + " , " );
						session.setAttribute("productHistory", productHistory);
						
						//System.out.println( productHistory);
						
						
					}
					// System.out.println("A" + productHistory);
					// productHistory+= "    " + productHistory ;
				}

			//	System.out.println("B" + productHistory);

				

				Iterator<Object> sideMenu = sideProducts.values().iterator();
				while (sideMenu.hasNext()) {
					SideMenu tmp = (SideMenu) sideMenu.next();
					Double price = (double) tmp.getPrice();
					String name = tmp.getName();

					if (productName.toString().equals(name)) {

						total = total + (price * totalNoOfProduct);

						session.setAttribute("total",(double) total);

						productHistory =productHistory +( totalNoOfProduct + "x" + productName + "@ " + price + " , " );
						session.setAttribute("productHistory", productHistory);
						
					}

				}
				
				
				
				//Side Dessert 
				

				Iterator<Object> tmpdessert = dessertProducts.values().iterator();
				while (tmpdessert.hasNext()) {
					Dessert tmp = (Dessert) tmpdessert.next();
					Double price = (double) tmp.getPrice();
					String name = tmp.getName();

					if (productName.toString().equals(name)) {

						total = total + (price * totalNoOfProduct);

						session.setAttribute("total", (double)total);

						productHistory =productHistory +( totalNoOfProduct + "x" + productName + "@ " + price + " , " );
						session.setAttribute("productHistory", productHistory);
						
					}

				}
				
				
				
				

			}
	}
	

	private void total(HttpServletRequest req, HttpServletResponse resp) // http://www.javacodegeeks.com/2014/03/how-hashmap-works-in-java.html
			throws ServletException, IOException {

		 totaltem(req);
	//	session.setAttribute("productHistory", productHistory);

		resp.sendRedirect("shop?action=viewTotal");
		// req.getRequestDispatcher("/WEB-INF/jsp/view/total.jsp").forward(req,resp);

	}

	private void transaction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException,
			ClassNotFoundException {

		HttpSession session = req.getSession();

		String userName = (String) session.getAttribute("userName");
		String tmpTotal = (String ) session.getAttribute("total");
		Double total = Double.parseDouble(tmpTotal);
		// System.out.println(total);

		Map<Object, Integer> carts = (Map<Object, Integer>) session
				.getAttribute("cart");

		
		// Object productName = carts.toString() ;
		String productHistory = (String) session.getAttribute("productHistory");

		Integer totalNoOfProduct = 0;
		 
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db", "root", "rooney");
		Statement st = con.createStatement();
		
	
		
		// ResultSet rs;
		int i = st
				.executeUpdate("insert into history(user_name, product_history, total_expense , purchase_date) values ('"
						+ userName
						+ "','"
						+ productHistory
						+ "','"
						+ total
						+ "', NOW())");


		req.getSession().removeAttribute("cart");
		req.getSession().removeAttribute("total");

		// this.empty(req, resp);

		req.getRequestDispatcher("/WEB-INF/jsp/view/transaction.jsp").forward(
				req, resp);

	}

	private void history(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		req.getRequestDispatcher("/WEB-INF/jsp/view/history.jsp").forward(req,
				resp);

	}

	@SuppressWarnings("unchecked")
	private void viewTotal(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<Object, Integer> cart = (Map<Object, Integer>) req.getSession()
				.getAttribute("cart");
		req.setAttribute("cart", cart);

		req.getRequestDispatcher("/WEB-INF/jsp/view/viewTotal.jsp").forward(
				req, resp);
	}

}