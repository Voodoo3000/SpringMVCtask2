package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.NewsDao;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;
import kz.epam.intlab.service.CommentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    @Mock
    private NewsDao mockDao;

    @Mock(name = "commentConverter")
    private DTOEntityConverter commentConverter;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void addComment() throws DaoException {
        //GIVEN
        int givenNewsId = 9;
        int givenCommentId = 1;

        News givenNews = new News();
        givenNews.setId(givenNewsId);
        givenNews.setTitle("Title of news");
        givenNews.setBrief("Brief of news");
        givenNews.setContent("Title of news");

        CommentDTO givenDTOComment = new CommentDTO();
        givenDTOComment.setCommentId(givenCommentId);
        givenDTOComment.setNewsId(givenNewsId);
        givenDTOComment.setCommentAuthor("Paulo");
        givenDTOComment.setCommentContent("Paulo was here");

        Comment givenComment = new Comment();
        givenComment.setId(givenCommentId);
        givenComment.setNewsId(givenNewsId);
        givenComment.setCommentAuthor("Paulo");
        givenComment.setCommentContent("Paulo was here");

        when(commentConverter.convertDTOToEntity(givenDTOComment)).thenReturn(givenComment);
        when(mockDao.getNewsById(givenNewsId)).thenReturn(givenNews);
        givenNews.getCommentList().add(givenComment);

        //WHEN
        commentService.addComment(givenDTOComment, givenNewsId);

        //THEN
        verify(mockDao).addUpdateNews(givenNews);
    }

    @Test
    public void deleteComment() throws DaoException {
        //GIVEN
        int givenNewsId = 9;
        int givenCommentId = 1;

        News givenNews = new News();
        givenNews.setId(givenNewsId);
        givenNews.setTitle("Title of news");
        givenNews.setBrief("Brief of news");
        givenNews.setContent("Title of news");

        when(mockDao.getNewsById(givenNewsId)).thenReturn(givenNews);

        List<Comment> comments = givenNews.getCommentList();
        Iterator<Comment> itr = comments.iterator();

        while (itr.hasNext()) {
            if (itr.next().getId() == givenCommentId) {
                itr.remove();
            }
        }

        givenNews.setCommentList(comments);

        //WHEN
        commentService.deleteComment(givenCommentId, givenNewsId);

        //THEN
        verify(mockDao).addUpdateNews(givenNews);
    }
}