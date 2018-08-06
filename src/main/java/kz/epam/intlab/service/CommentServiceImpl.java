package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.Dao;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Iterator;
import java.util.List;

@Stateless
public class CommentServiceImpl implements CommentService {

    @EJB
    private Dao newsDao;

    @EJB(beanName = "CommentConverterImpl")
    private DTOEntityConverter commentConverter;

    @Override
    public void addComment(CommentDTO commentModel, int id) throws DaoException {
        Comment comment = (Comment) commentConverter.convertDTOToEntity(commentModel);
        News news = newsDao.getNewsById(id);
        news.getCommentList().add(comment);
        newsDao.addUpdateNews(news);
    }

    @Override
    public void deleteComment(int commentId, int newsId) throws DaoException {
        News news = newsDao.getNewsById(newsId);
        List<Comment> comments = news.getCommentList();
        Iterator<Comment> itr = comments.iterator();

        while (itr.hasNext()) {
            if (itr.next().getId() == commentId) {
                itr.remove();
            }
        }
        news.setCommentList(comments);
        newsDao.addUpdateNews(news);
    }
}
