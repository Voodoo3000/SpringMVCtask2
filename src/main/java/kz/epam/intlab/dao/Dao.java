package kz.epam.intlab.dao;

import kz.epam.intlab.entity.News;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface Dao {

    void addUpdateNews(News newsBean) throws DaoException;

    Map<Integer, News> getAllNews();

    News getNewsById(Integer id) throws DaoException;

    void deleteNews(News newsBean) throws DaoException;
}
