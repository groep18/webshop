package ui.controller.handler.product;

import domain.model.Product;
import domain.service.ShopService;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductAddHandler extends RequestHandler {

	public ProductAddHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		Product p = new Product();
		
		setProductId(request, errors, p);
		setDescription(request, errors, p);
		setPrice(request, errors, p);

		request.setAttribute("product", p);
		
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			this.handlerFactory.getHandler("productFormAdd").handleRequest(request, response);
		} else {
			try {
				this.shopService.addProduct(p);
				response.sendRedirect("Controller?action=productOverview");
			} catch(Exception e) {
				errors.put("Add error", e.getMessage());
				request.setAttribute("errors", errors);
				this.handlerFactory.getHandler("productFormAdd").handleRequest(request, response);
			}
		}
	}
	
}
