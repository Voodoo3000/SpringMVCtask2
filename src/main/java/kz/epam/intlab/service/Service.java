package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.DTOModel;
import kz.epam.intlab.entity.News;

import java.util.Map;

public interface Service {
    public Map<Integer, News> getAllNews();
    public void addUpdateNews(DTOModel model) throws DaoException;
    public DTOModel getNewsById(int id) throws DaoException;
    public void deleteNews(int id) throws DaoException;
    public void addComment(DTOModel model, int id) throws DaoException;
    public void deleteComment(int commentId, int newId) throws DaoException;
}
