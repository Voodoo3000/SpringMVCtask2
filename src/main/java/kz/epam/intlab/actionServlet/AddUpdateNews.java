package kz.epam.intlab.actionServlet;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.service.Service;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addUpNews")
public class AddUpdateNews extends HttpServlet {

    @EJB
    private Service service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(Integer.parseInt(req.getParameter("newsId")));
        newsDTO.setDate(req.getParameter("date"));
        newsDTO.setTitle(req.getParameter("title"));
        newsDTO.setBrief(req.getParameter("brief"));
        newsDTO.setContent(req.getParameter("content"));

        try {
            req.setAttribute("newsModel", service.addUpdateNews(newsDTO));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/pages/selected_news.jsp").forward(req, resp);
    }
}
