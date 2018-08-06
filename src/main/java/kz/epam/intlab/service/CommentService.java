package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.CommentDTO;

import javax.ejb.Local;

@Local
public interface CommentService {
    public void addComment(CommentDTO commentModel, int id) throws DaoException;
    public void deleteComment(int commentId, int newsId) throws DaoException;
}
