package kz.epam.intlab.actionServlet.commentAction;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.service.Service;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteComment")
public class DeleteComment extends HttpServlet {

    @EJB
    private Service service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameterValues("deleteCommentCheckbox") != null) {
            String[] deleteCommentCheckbox = req.getParameterValues("deleteCommentCheckbox");
            int newsId = Integer.parseInt(req.getParameter("newsId"));
            int commentId;
            for (String s : deleteCommentCheckbox) {
                commentId = Integer.parseInt(s);
                try {
                    service.deleteComment(commentId, newsId);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
        }
        req.getRequestDispatcher("/openSelectedNews").forward(req, resp);
    }
}
