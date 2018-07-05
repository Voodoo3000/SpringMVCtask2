package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.NewsDao;
import kz.epam.intlab.dao.UserDao;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;
import kz.epam.intlab.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ServiceImpl implements Service {

    @Autowired
    private UserDao userDao;
    @Autowired
    private NewsDao newsDao;
    @Autowired
    @Qualifier("newsConverter")
    private DTOEntityConverter newsConverter;
    @Autowired
    @Qualifier("commentConverter")
    private DTOEntityConverter commentConverter;

    @Autowired
    @Qualifier("userConverter")
    private DTOEntityConverter userConverter;

    @Override
    public Map<Integer, News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public void addUpdateNews(NewsDTO newsModel) throws DaoException {
        newsDao.addUpdateNews((News) newsConverter.convertDTOToEntity(newsModel));
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
