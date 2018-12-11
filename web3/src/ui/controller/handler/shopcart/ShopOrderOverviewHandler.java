package controller.handler.shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.handler.HandlerFactory;
import controller.handler.RequestHandler;
import exception.NotAuthorizedException;
import model.user.Role;
import service.ShopService;

public class ShopOrderOverviewHandler extends RequestHandler {

	public ShopOrderOverviewHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, IOException, ServletException {
		Role[] roles = {Role.CUSTOMER, Role.ADMINISTRATOR};
		checkRole(request, roles);

		RequestDispatcher view = request.getRequestDispatcher("shopOrderOverview.jsp");
		view.forward(request, response);
	}
	
}
