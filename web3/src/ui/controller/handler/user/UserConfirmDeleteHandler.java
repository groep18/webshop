package controller.handler.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.handler.HandlerFactory;
import controller.handler.RequestHandler;
import exception.NotAuthorizedException;
import model.user.Role;
import model.user.User;
import service.ShopService;

public class UserConfirmDeleteHandler extends RequestHandler {

	public UserConfirmDeleteHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException, ServletException {
		Role[] roles = {Role.ADMINISTRATOR};
		checkRole(request, roles);
		
		String id = request.getParameter("id");
		
		try {
			User u = this.shopService.getUser(id);
			request.setAttribute("user", u);
			RequestDispatcher view = request.getRequestDispatcher("userConfirmDelete.jsp");
			view.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			this.handlerFactory.getHandler("userOverview").handleRequest(request, response);
		}
	}

}
