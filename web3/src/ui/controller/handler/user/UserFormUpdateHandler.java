package ui.controller.handler.user;

import domain.model.NotAuthorizedException;
import domain.model.Role;
import domain.model.User;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserFormUpdateHandler extends RequestHandler {

	public UserFormUpdateHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotAuthorizedException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		checkRole(request, roles);
		
		request.setAttribute("roles", Role.values());
		String id = request.getParameter("id");
		
		try {
			User u = this.shopService.getUser(id);
			request.setAttribute("user", u);
		} catch(Exception e) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("getPerson error", e.getMessage());
			request.setAttribute("errors", errors);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("userFormUpdate.jsp");
		view.forward(request, response);

	}

}
