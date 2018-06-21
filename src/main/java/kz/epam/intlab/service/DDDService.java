package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.NewsDao;
import kz.epam.intlab.dto.DTOModel;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class DDDService implements Service {

    @Autowired
    private NewsDao newsDao;
    @Autowired
    @Qualifier("newsConverter")
    private DTOEntityConverter newsConverter;
    @Autowired
    @Qualifier("commentConverter")
    private DTOEntityConverter commentConverter;

    @Override
    public Map<Integer, News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public void addUpdateNews(DTOModel model) throws DaoException {
        newsDao.addUpdateNews((News) newsConverter.convertDTOToEntity(model));
    }

    @Override
    public DTOModel getNewsById(int id) throws DaoException {
        return newsConverter.convertEntityToDTO(newsDao.getNewsById(id));
    }

    @Override
    public void deleteNews(int newsId) throws DaoException {
        newsDao.deleteNews(newsDao.getNewsById(newsId));
    }

    @Override
    public void addComment(DTOModel model, int id) throws DaoException {
        Comment comment = (Comment) commentConverter.convertDTOToEntity(model);
        News news = newsDao.getNewsById(id);
        news.getCommentList().add(comment);
        newsDao.addUpdateNews(news);
    }

    @Override
    public void deleteComment(int commentId, int newId) throws DaoException {
        News news = newsDao.getNewsById(newId);
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
