package ui.controller.handler.product;

import domain.db.DbException;
import domain.service.ShopService;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductDeleteHandler extends RequestHandler {

	public ProductDeleteHandler(ShopService shopService, HandlerFactory handlerFactory) {super(shopService, handlerFactory);}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		try {
			this.shopService.deleteProduct(id);
		} catch (DbException e) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ShopService deleteProduct() error", e.getMessage());
			request.setAttribute("errors", errors);
		}
		response.sendRedirect("Controller?action=productOverview");
	}
	
}
