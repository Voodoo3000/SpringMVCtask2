package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.dto.UserDTO;
import kz.epam.intlab.entity.News;

import java.util.Map;

public interface Service {
    public Map<Integer, News> getAllNews();
    public void addUpdateNews(NewsDTO newsModel) throws DaoException;
    public NewsDTO getNewsById(int id) throws DaoException;
    public void deleteNews(int id) throws DaoException;
    public void addComment(CommentDTO commentModel, int id) throws DaoException;
    public void deleteComment(int commentId, int newId) throws DaoException;
    public void addUpdateUser(UserDTO userDTO) throws DaoException;
    public UserDTO getUserByEmail(String email) throws DaoException;
}
