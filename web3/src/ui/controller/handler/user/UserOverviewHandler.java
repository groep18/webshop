package ui.controller.handler.user;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.User;
import domain.service.ShopService;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserOverviewHandler extends RequestHandler {

	public UserOverviewHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> users;
		try {
			users = this.shopService.getUsers();
			request.setAttribute("users", users);
		} catch (DbException | DomainException e) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ShopService getPersons() error", e.getMessage());
			request.setAttribute("errors", errors);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("personoverview.jsp");
		view.forward(request, response);
	}

}
