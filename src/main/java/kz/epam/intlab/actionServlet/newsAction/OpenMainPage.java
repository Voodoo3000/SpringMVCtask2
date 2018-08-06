package kz.epam.intlab.actionServlet.newsAction;

import kz.epam.intlab.service.NewsService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class OpenMainPage extends HttpServlet {

    @EJB
    private NewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("newsDTOMap", newsService.getAllNews());

        req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
    }
}
