package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.NewsDTO;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface NewsService {
    public Map<Integer, NewsDTO> getAllNews();
    public NewsDTO addUpdateNews(NewsDTO newsModel) throws DaoException;
    public NewsDTO getNewsById(int id) throws DaoException;
    public void deleteNews(int id) throws DaoException;
}
