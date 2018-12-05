package ui.controller.handler.user;

import domain.model.User;
import domain.service.ShopService;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserConfirmDeleteHandler extends RequestHandler {

	public UserConfirmDeleteHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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
