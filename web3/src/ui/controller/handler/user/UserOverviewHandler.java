package ui.controller.handler.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.NotAuthorizedException;
import domain.model.Role;
import domain.model.User;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

public class UserOverviewHandler extends RequestHandler {

	public UserOverviewHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		checkRole(request, roles);
		
		List<User> users;
		try {
			users = this.shopService.getUsers();
			request.setAttribute("users", users);
		} catch (DbException | DomainException e) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ShopService getPersons() error", e.getMessage());
			request.setAttribute("errors", errors);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("userOverview.jsp");
		view.forward(request, response);
	}

}
