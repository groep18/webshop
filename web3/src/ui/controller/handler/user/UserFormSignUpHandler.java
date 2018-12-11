package ui.controller.handler.user;

import domain.model.Role;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserFormSignUpHandler extends RequestHandler {

	public UserFormSignUpHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("roles", Role.values());
		
		RequestDispatcher view = request.getRequestDispatcher("userFormSignUp.jsp");
		view.forward(request, response);
	}

}
