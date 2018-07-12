package kz.epam.intlab.dao;

import kz.epam.intlab.entity.News;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface Dao {

    News addUpdateNews(News news) throws DaoException;

    Map<Integer, News> getAllNews();

    News getNewsById(Integer id) throws DaoException;

    void deleteNews(News news) throws DaoException;
}
