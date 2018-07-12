package kz.epam.intlab.actionServlet;

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
public class OpenMainPage extends HttpServlet {

    @EJB
    private Service service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("newsMap", service.getAllNews());

        req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
    }
}
