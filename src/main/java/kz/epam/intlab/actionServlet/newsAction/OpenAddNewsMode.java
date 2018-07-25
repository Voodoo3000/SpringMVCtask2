package kz.epam.intlab.actionServlet.newsAction;

import kz.epam.intlab.dto.NewsDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/openAddNews")
public class OpenAddNewsMode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("newsModel", new NewsDTO());
        req.getRequestDispatcher("/WEB-INF/pages/edit_mode.jsp").forward(req, resp);
    }
}
