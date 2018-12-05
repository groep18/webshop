package ui.controller.handler.product;

import domain.model.Product;
import domain.service.ShopService;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductConfirmDeleteHandler extends RequestHandler {

	public ProductConfirmDeleteHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String id = request.getParameter("id");
		
		try {
			Product p = this.shopService.getProduct(id);
			request.setAttribute("product", p);
			RequestDispatcher view = request.getRequestDispatcher("productConfirmDelete.jsp");
			view.forward(request, response);
		} catch(Exception e) {
			this.handlerFactory.getHandler("productOverview").handleRequest(request, response);
		}
	}
	
}
