package ui.controller;

import domain.db.PersonDBSQL;
import domain.db.PersonDbInMemory;
import domain.db.ProductDBSQL;
import domain.db.ProductDbInMemory;
import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/Controller")
public class Servlet extends HttpServlet {
    private ShopService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();

        Properties properties = new Properties();
        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("password", context.getInitParameter("password"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("sslmode", context.getInitParameter("sslmode"));
        properties.setProperty("currentSchema", context.getInitParameter("currentSchema"));
        properties.setProperty("url", context.getInitParameter("url"));

        service = new ShopService(properties);

    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "index";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        String destination;
        switch (action) {
            case "index":
                destination = index(request, response);
                break;
            case "products":
                destination = products(request, response);
                break;
            case "overview":
                destination = overview(request, response);
                break;
            case "signUp":
                destination = signUp(request, response);
                break;
/*
            case "signUpAction":
                destination = signUpAction(request, response);
                break;
*/
            default:
                destination = index(request, response);
                break;
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String index(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }

    private String products(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = service.getProducts();
        request.setAttribute("productDB", products);
        return "productoverview.jsp";
    }

    private String overview(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons = service.getPersons();
        request.setAttribute("personDB", persons);
        return "personoverview.jsp";
    }

    private String signUp(HttpServletRequest request, HttpServletResponse response) {
        return "signUp.jsp";
    }

    /*
    private String signUpAction(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errorlist = new ArrayList<>();
        Person person = new Person();
        personDB = service.getPersons();
        getUserID(person, request, errorlist);
        getEmail(person, request, errorlist);
        getPassword(person, request, errorlist);
        getFirstName(person, request, errorlist);
        getLastName(person, request, errorlist);

        if (errorlist.size() == 0) {
            try {
                personDB.add(person);
                request.setAttribute("personDB", personDB);
                return "personoverview.jsp";
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                return "signUp.jsp";
            }
        } else {
            request.setAttribute("errorlist", errorlist);
            return "signUp.jsp";
        }
    }*/

    private void getUserID(Person person, HttpServletRequest request, ArrayList<String> errorlist) {
        String userID = request.getParameter("userid");
        try {
            person.setUserid(userID);
            request.setAttribute("userIDClass", "has-success");
            request.setAttribute("userIDPreviousValue", userID);
        } catch (IllegalArgumentException e) {
            errorlist.add(e.getMessage());
            request.setAttribute("userIDClass", "has-error");
        }
    }

    private void getFirstName(Person person, HttpServletRequest request, ArrayList<String> errorlist) {
        String firstName = request.getParameter("firstName");
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNameClass", "has-success");
            request.setAttribute("firstNamePreviousValue", firstName);
        } catch (IllegalArgumentException e) {
            errorlist.add(e.getMessage());
            request.setAttribute("firstNameClass", "has-error");
        }
    }

    private void getLastName(Person person, HttpServletRequest request, ArrayList<String> errorlist) {
        String lastName = request.getParameter("lastName");
        try {
            person.setLastName(lastName);
            request.setAttribute("lastNameClass", "has-success");
            request.setAttribute("lastNamePreviousValue", lastName);
        } catch (IllegalArgumentException e) {
            errorlist.add(e.getMessage());
            request.setAttribute("lastNameClass", "has-error");
        }
    }

    private void getEmail(Person person, HttpServletRequest request, ArrayList<String> errorlist) {
        String email = request.getParameter("email");
        try {
            person.setEmail(email);
            request.setAttribute("emailClass", "has-success");
            request.setAttribute("emailPreviousValue", email);
        } catch (IllegalArgumentException e) {
            errorlist.add(e.getMessage());
            request.setAttribute("emailClass", "has-error");
        }
    }

    private void getPassword(Person person, HttpServletRequest request, ArrayList<String> errorlist) {
        String password = request.getParameter("password");
        try {
            person.setPassword(password);
            request.setAttribute("passwordClass", "has-success");
            request.setAttribute("passwordPreviousValue", password);
        } catch (IllegalArgumentException e) {
            errorlist.add(e.getMessage());
            request.setAttribute("passwordClass", "has-error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
