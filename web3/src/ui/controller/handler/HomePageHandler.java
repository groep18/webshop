package ui.controller.handler;

import domain.service.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HomePageHandler extends RequestHandler {

	public HomePageHandler(ShopService shopService, HandlerFactory handlerFactory) {
		super(shopService, handlerFactory);
	}
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

}
