package ui.controller.handler.product;

import domain.db.DbException;
import domain.model.Product;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductOverviewHandler extends RequestHandler {

	public ProductOverviewHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products;
		try {
			products = this.shopService.getProducts();

			request.setAttribute("products", products);
			RequestDispatcher view = request.getRequestDispatcher("productOverview.jsp");
			view.forward(request, response);
		} catch (DbException e) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ShopService getProducts() error", e.getMessage());
			request.setAttribute("errors", errors);
		}
	}

}
