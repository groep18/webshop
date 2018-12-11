package ui.controller.handler.shopcart;

import domain.model.DomainException;
import domain.model.NotAuthorizedException;
import domain.model.Role;
import domain.model.shop.Cart;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShopCartUpdateHandler extends RequestHandler {

	public ShopCartUpdateHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, IOException, ServletException {
		Role[] roles = {Role.CUSTOMER, Role.ADMINISTRATOR};
		checkRole(request, roles);
		
		Map<String, String> errors = new HashMap<String, String>();
		
		//Parse quantity String as integer
		String quantityString = request.getParameter("quantity");
		int quantity = 0;
		try {
			quantity = Integer.parseInt(quantityString);
		} catch (NumberFormatException e) {
			errors.put("Add to cart error", "Please enter a valid integer as quantity.");
			request.setAttribute("errors", errors);
			this.handlerFactory.getHandler("shopCartOverview").handleRequest(request, response);
			return;
		}
		
		//Get poduct ID
		String id = request.getParameter("id");
		
		//Get Cart from session
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");

		//Edit the quantity
		try {
			cart.replaceQuantityOrdered(id, quantity);
		} catch (DomainException e) {
			errors.put("Add to cart error", e.getMessage());
			request.setAttribute("errors", errors);
			this.handlerFactory.getHandler("productOverview").handleRequest(request, response);
			return;
		}
		
		//Add cart (back) to the session
		session.setAttribute("cart", cart);
		
		//Go to cart view
		response.sendRedirect("Controller?action=shopCartOverview");
	}
	
}
