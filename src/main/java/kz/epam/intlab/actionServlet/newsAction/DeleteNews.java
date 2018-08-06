package kz.epam.intlab.actionServlet.newsAction;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.service.NewsService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteNews")
public class DeleteNews extends HttpServlet {

    @EJB
    private NewsService newsService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameterValues("deleteNewsCheckbox") != null) {
                String[] deleteNewsCheckbox = req.getParameterValues("deleteNewsCheckbox");
                int id;
                for (String s : deleteNewsCheckbox) {
                    id = Integer.parseInt(s);
                    newsService.deleteNews(id);
                }
            } else {
                int newsIdForDel = Integer.parseInt(req.getParameter("newsIdForDel"));
                newsService.deleteNews(newsIdForDel);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/main");
    }
}
