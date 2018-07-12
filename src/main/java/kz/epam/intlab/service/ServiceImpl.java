package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.Dao;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.NewsDao;
import kz.epam.intlab.dao.UserDao;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;
import kz.epam.intlab.entity.User;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Stateless
@Local(Service.class)
public class ServiceImpl implements Service {

    @EJB
    private UserDao userDao;

    @EJB
    private Dao newsDao;

    @EJB(beanName = "NewsConverterImpl")
    private DTOEntityConverter newsConverter;

    @EJB(beanName = "CommentConverterImpl")
    private DTOEntityConverter commentConverter;

    @EJB(beanName = "UserConverterImpl")
    private DTOEntityConverter userConverter;

    @Override
    public Map<Integer, News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public NewsDTO addUpdateNews(NewsDTO newsModel) throws DaoException {
        News news = newsDao.addUpdateNews((News) newsConverter.convertDTOToEntity(newsModel));
        return (NewsDTO) newsConverter.convertEntityToDTO(news);
    }

    @Override
    public NewsDTO getNewsById(int id) throws DaoException {
        return (NewsDTO) newsConverter.convertEntityToDTO(newsDao.getNewsById(id));
    }

    @Override
    public void deleteNews(int newsId) throws DaoException {
        newsDao.deleteNews(newsDao.getNewsById(newsId));
    }

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

    @Override
    public void addUpdateUser(UserDTO userDTO) throws DaoException {
        userDao.addUpdateUser((User) userConverter.convertDTOToEntity(userDTO));
    }

    public UserDTO getUserByEmail(String email) throws DaoException {
        UserDTO userDTO = null;

        if (userDao.getUserByEmail(email) != null) {
            userDTO = (UserDTO) userConverter.convertEntityToDTO(userDao.getUserByEmail(email));
        }
        return userDTO;
    }
}
