package kz.epam.intlab.actionServlet.newsAction;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.service.NewsService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/openEditMode")
public class OpenEditNewsMode extends HttpServlet {

    @EJB
    private NewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NewsDTO newsDTO = null;
        int newsId = Integer.parseInt(req.getParameter("newsId"));
        try {
            newsDTO = newsService.getNewsById(newsId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        req.setAttribute("newsModel", newsDTO);
        req.getRequestDispatcher("/WEB-INF/pages/edit_mode.jsp").forward(req, resp);
    }
}
