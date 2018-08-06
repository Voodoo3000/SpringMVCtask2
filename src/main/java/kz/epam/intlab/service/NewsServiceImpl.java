package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.Dao;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.entity.News;

import javax.ejb.*;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class NewsServiceImpl implements NewsService {

    @EJB
    private Dao newsDao;

    @EJB(beanName = "NewsConverterImpl")
    private DTOEntityConverter newsConverter;

    @Override
    public Map<Integer, NewsDTO> getAllNews() {
        NewsDTO newsDTO;
        Map<Integer, NewsDTO> newsDTOMap = new HashMap<>();

        Map<Integer, News> newsEntityMap = newsDao.getAllNews();

        for (News news : newsEntityMap.values()) {
            newsDTO = (NewsDTO) newsConverter.convertEntityToDTO(news);
            newsDTOMap.put(newsDTO.getId(), newsDTO);
        }
        return newsDTOMap;
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
}
