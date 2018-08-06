package kz.epam.intlab.RESTfulWebService;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.service.CommentService;
import kz.epam.intlab.service.NewsService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/commentWS")
@Stateless
@LocalBean
public class CommentWebService {

    @EJB
    private NewsService newsService;

    @EJB
    private CommentService commentService;

    @GET
    @Path("/getById/{newsId}/{commentId}")
    public CommentDTO getById (@PathParam("newsId") int newsId, @PathParam("commentId") int commentId) throws DaoException {
        NewsDTO newsDTO = newsService.getNewsById(newsId);
        CommentDTO chosenCommentDto = null;
        for (CommentDTO commentDTO : newsDTO.getDTOCommentList()) {
            if(commentDTO.getCommentId() == commentId) {
                chosenCommentDto = commentDTO;
            }
        }
        return chosenCommentDto;
    }

    @GET
    @Path("/getAllByNewsId/{id}")
    public List<CommentDTO> getAll(@PathParam("id") int id) throws DaoException {
        List<CommentDTO> commentDTOList;
        commentDTOList = newsService.getNewsById(id).getDTOCommentList();
        return commentDTOList;
    }

    @POST
    @Path("/add")
    public void add(CommentDTO commentDTO) throws DaoException {
        commentService.addComment(commentDTO, commentDTO.getNewsId());
    }

    @POST
    @Path("/delete/{newsId}/{commentId}")
    public void delete(@PathParam("newsId") int newsId, @PathParam("commentId") int commentId) throws DaoException {
        commentService.deleteComment(newsId, commentId);
    }
}
