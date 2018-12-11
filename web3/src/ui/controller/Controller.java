package ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.NotAuthorizedException;
import domain.service.ShopService;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopService shopService;
	private HandlerFactory handlerFactory;

	public Controller() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();

		ServletContext context = getServletContext();

		Properties properties = new Properties();
		Enumeration<String> parameterNames = context.getInitParameterNames();
		while(parameterNames.hasMoreElements()) {
			String popertyName = parameterNames.nextElement();
			properties.setProperty(popertyName, context.getInitParameter(popertyName));
		}

		try {

			this.shopService = new ShopService(properties);
			InputStream input = context.getResourceAsStream("/WEB-INF/handler.xml");
			Properties handlerProperties = new Properties();
			handlerProperties.loadFromXML(input);
			this.handlerFactory = new HandlerFactory(handlerProperties, this.shopService);

		} catch (Exception e) {
			e.getMessage();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} else {
			try {
				this.handlerFactory.getHandler(action).handleRequest(request, response);
			} catch (NotAuthorizedException e) {
				e.printStackTrace();
			}
		}
	}


}
