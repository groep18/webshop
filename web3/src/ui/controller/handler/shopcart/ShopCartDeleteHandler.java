package ui.controller.handler.shopcart;

import domain.model.NotAuthorizedException;
import domain.model.Role;
import domain.model.shop.Cart;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShopCartDeleteHandler extends RequestHandler {

	public ShopCartDeleteHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, IOException, ServletException {
		Role[] roles = {Role.CUSTOMER, Role.ADMINISTRATOR};
		checkRole(request, roles);
		
		//Get poduct ID
		String id = request.getParameter("id");
		
		//Get Cart from session
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		
		//Delete product from cart
		cart.deleteProduct(id);

		//Add cart (back) to the session
		session.setAttribute("cart", cart);
		
		//Go to cart view
		response.sendRedirect("Controller?action=shopCartOverview");
	}
	
}
