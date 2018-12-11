package ui.controller.handler.product;

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


public class ProductFormAddHandler extends RequestHandler {

	public ProductFormAddHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotAuthorizedException {
		Role[] roles = {Role.ADMINISTRATOR};
		checkRole(request, roles);

		RequestDispatcher view = request.getRequestDispatcher("productFormAdd.jsp");
		view.forward(request, response);
	}
	
}
