package ui.controller.handler.user;

import domain.db.DbException;
import domain.model.NotAuthorizedException;
import domain.model.Role;
import domain.model.User;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserDeleteHandler extends RequestHandler {

	public UserDeleteHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException, ServletException {
		Role[] roles = {Role.ADMINISTRATOR};
		checkRole(request, roles);
		
		String id = request.getParameter("id");
		try {
			this.shopService.deleteUser(id);
			HttpSession session = request.getSession();
			User currentUser = (User)session.getAttribute("user");
			if(!id.equals(currentUser.getId())) {
				response.sendRedirect("Controller?action=userOverview");
			} else {
				this.handlerFactory.getHandler("userLogout").handleRequest(request, response);
			}
		} catch (DbException e) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ShopService deletePerson() error", e.getMessage());
			request.setAttribute("errors", errors);
			this.handlerFactory.getHandler("userConfirmDelete").handleRequest(request, response);
		}
	}

}
