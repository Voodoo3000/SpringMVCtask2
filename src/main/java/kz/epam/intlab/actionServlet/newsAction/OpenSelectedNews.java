package kz.epam.intlab.actionServlet.newsAction;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.service.Service;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/openSelectedNews")
public class OpenSelectedNews extends HttpServlet {

    @EJB
    private Service service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("newsId"));
        try {
            req.setAttribute("newsModel", service.getNewsById(id));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/pages/selected_news.jsp").forward(req, resp);
    }
}
