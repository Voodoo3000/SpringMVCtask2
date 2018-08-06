package kz.epam.intlab.actionServlet.commentAction;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.service.CommentService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addComment")
public class AddComment extends HttpServlet {

    @EJB
    private CommentService commentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int newsId = Integer.parseInt(req.getParameter("newsId"));
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setNewsId(newsId);
        commentDTO.setCommentAuthor(req.getParameter("commentAuthor"));
        commentDTO.setCommentContent(req.getParameter("commentContent"));

        try {
            commentService.addComment(commentDTO, newsId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/openSelectedNews").forward(req, resp);
    }
}
