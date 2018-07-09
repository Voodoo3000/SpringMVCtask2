package kz.epam.intlab.controller;

import kz.epam.intlab.service.Service;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class OpenMainPageAction extends HttpServlet {

    @EJB
    private Service service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("newsMap", service.getAllNews());
        System.out.println(service);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/main.jsp");
        requestDispatcher.forward(req, resp);
//        req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
    }
}
