package ui.controller.handler.user;

import domain.service.ShopService;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFormSignUpHandler extends RequestHandler {
    public UserFormSignUpHandler(ShopService shopService, HandlerFactory handlerFactory) {
        super(shopService, handlerFactory);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher view = request.getRequestDispatcher("signUp.jsp");
        view.forward(request, response);
    }
}
