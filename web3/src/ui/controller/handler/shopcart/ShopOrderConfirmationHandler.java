package ui.controller.handler.shopcart;

import domain.model.NotAuthorizedException;
import domain.model.Role;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class ShopOrderConfirmationHandler extends RequestHandler {

	public ShopOrderConfirmationHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, IOException, ServletException {
		Role[] roles = {Role.CUSTOMER, Role.ADMINISTRATOR};
		checkRole(request, roles);

		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		
		RequestDispatcher view = request.getRequestDispatcher("shopOrderConfirmation.jsp");
		view.forward(request, response);
	}
	
}
