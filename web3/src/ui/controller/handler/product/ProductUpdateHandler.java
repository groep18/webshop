package ui.controller.handler.product;

import domain.db.DbException;
import domain.model.NotAuthorizedException;
import domain.model.Product;
import domain.model.Role;
import domain.service.ShopService;
import ui.controller.HandlerFactory;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class ProductUpdateHandler extends RequestHandler {

	public ProductUpdateHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException, ServletException {
		Role[] roles = {Role.ADMINISTRATOR};
		checkRole(request, roles);
		
		Map<String, String> errors = new HashMap<String, String>();
		
		
		String id = request.getParameter("id");
		
		Product p = null;
		try {
			p = this.shopService.getProduct(id);
		}  catch(Exception e) {
			errors.put("Update error", e.getMessage());
			request.setAttribute("errors", errors);
			this.handlerFactory.getHandler("productOverview").handleRequest(request, response);
		}
		
		setDescription(request, errors, p);
		setPrice(request, errors, p);

		request.setAttribute("product", p);
		
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			this.handlerFactory.getHandler("productFormUpdate").handleRequest(request, response);
		} else {
			try {
				this.shopService.updateProduct(p);
				response.sendRedirect("Controller?action=productOverview");
			} catch (DbException e) {
				errors.put("update error", e.getMessage());
				request.setAttribute("errors", errors);
				this.handlerFactory.getHandler("productFormUpdate").handleRequest(request, response);
			}
		}
	}
	
}
