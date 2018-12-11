package ui.controller.handler.product;

import domain.model.NotAuthorizedException;
import domain.model.Product;
import domain.model.Role;
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

public class ProductFormUpdateHandler extends RequestHandler {

	public ProductFormUpdateHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotAuthorizedException {
		Role[] roles = {Role.ADMINISTRATOR};
		checkRole(request, roles);
		
		String id = request.getParameter("id");

		try {
			Product p = this.shopService.getProduct(id);
			request.setAttribute("product", p);
		} catch(Exception e) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("getProduct error", e.getMessage());
			request.setAttribute("errors", errors);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("productFormUpdate.jsp");
		view.forward(request, response);
	}
	
}
