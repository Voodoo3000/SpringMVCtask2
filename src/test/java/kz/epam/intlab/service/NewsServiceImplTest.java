package kz.epam.intlab.service;

import kz.epam.intlab.converter.DTOEntityConverter;
import kz.epam.intlab.dao.Dao;
import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.entity.News;
import kz.epam.intlab.service.NewsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(1);

    private NewsDTO getGivenNewsDTO() {
        NewsDTO givenNewsDTO = new NewsDTO();
        givenNewsDTO.setId(AUTO_ID.getAndIncrement());
        return givenNewsDTO;
    }

    @Mock(name = "newsDao")
    private Dao mockDao;

    @Mock(name = "newsConverter")
    private DTOEntityConverter newsConverter;

    @InjectMocks
    private NewsServiceImpl newsService;

    @Test
    public void getAllNews() {
        //GIVEN
        Map<Integer, NewsDTO> actualResultMap;

        Map<Integer, News> givenNewsMap = new HashMap<>();
        givenNewsMap.put(AUTO_ID.getAndIncrement(), new News());
        givenNewsMap.put(AUTO_ID.getAndIncrement(), new News());
        givenNewsMap.put(AUTO_ID.getAndIncrement(), new News());

        when(mockDao.getAllNews()).thenReturn(givenNewsMap);
        when(newsConverter.convertEntityToDTO(any(News.class))).thenReturn(getGivenNewsDTO(), getGivenNewsDTO(), getGivenNewsDTO());

        //WHEN
        actualResultMap = newsService.getAllNews();

        //THEN
        assertEquals(givenNewsMap.size(), actualResultMap.size());
    }

    @Test
    public void addUpdateNews() throws DaoException {
        //GIVEN
        int givenNewsId = 9;

        NewsDTO actualResult;

        News givenNews = new News();

        NewsDTO givenNewsDTO = new NewsDTO();
        givenNewsDTO.setId(givenNewsId);
        givenNewsDTO.setTitle("Title of news");
        givenNewsDTO.setBrief("Brief of news");
        givenNewsDTO.setContent("Title of news");

        when(newsConverter.convertDTOToEntity(givenNewsDTO)).thenReturn(givenNews);
        when(newsConverter.convertEntityToDTO(givenNews)).thenReturn(givenNewsDTO);
        when(mockDao.addUpdateNews(givenNews)).thenReturn(givenNews);

        //WHEN
        actualResult = newsService.addUpdateNews(givenNewsDTO);

        //THEN
        assertEquals(givenNewsDTO.getId(), actualResult.getId());
        assertEquals(givenNewsDTO.getTitle(), actualResult.getTitle());
        assertEquals(givenNewsDTO.getBrief(), actualResult.getBrief());
        assertEquals(givenNewsDTO.getContent(), actualResult.getContent());
    }

    @Test
    public void getNewsById() throws DaoException {
        //GIVEN
        int givenNewsId = 9;

        News expectedNews = new News();

        NewsDTO actualResult;

        NewsDTO expectedNewsDTO = new NewsDTO();
        expectedNewsDTO.setId(givenNewsId);
        expectedNewsDTO.setTitle("Title of news");
        expectedNewsDTO.setBrief("Brief of news");
        expectedNewsDTO.setContent("Title of news");

        when(mockDao.getNewsById(givenNewsId)).thenReturn(expectedNews);
        when(newsConverter.convertEntityToDTO(expectedNews)).thenReturn(expectedNewsDTO);

        //WHEN
        actualResult = newsService.getNewsById(givenNewsId);

        //THEN
        assertEquals(expectedNewsDTO.getId(), actualResult.getId());
        assertEquals(expectedNewsDTO.getTitle(), actualResult.getTitle());
        assertEquals(expectedNewsDTO.getBrief(), actualResult.getBrief());
        assertEquals(expectedNewsDTO.getContent(), actualResult.getContent());
    }

    @Test
    public void deleteNews() throws DaoException {
        //GIVEN
        int givenNewsId = 9;

        News expectedNews = new News();
        expectedNews.setId(givenNewsId);
        expectedNews.setTitle("Title of news");
        expectedNews.setBrief("Brief of news");
        expectedNews.setContent("Title of news");

        when(mockDao.getNewsById(givenNewsId)).thenReturn(expectedNews);

        //WHEN
        newsService.deleteNews(givenNewsId);

        //THEN
        verify(mockDao).deleteNews(expectedNews);
    }
}