package kz.epam.intlab.converter;

import kz.epam.intlab.dto.NewsDTO;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;

import javax.ejb.Stateless;

@Stateless
public class NewsConverterImpl implements DTOEntityConverter<News, NewsDTO> {

    @Override
    public News convertDTOToEntity(NewsDTO newsDTO) {

        News news = new News();
        news.setId(newsDTO.getId());
        news.setDate(newsDTO.getDate());
        news.setTitle(newsDTO.getTitle());
        news.setBrief(newsDTO.getBrief());
        news.setContent(newsDTO.getContent());

        return news;
    }

    @Override
    public NewsDTO convertEntityToDTO(News news) {

        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setDate(news.getDate());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setBrief(news.getBrief());
        newsDTO.setContent(news.getContent());

        for(Comment comment: news.getCommentList()) {
            newsDTO.getDTOCommentList().add(comment);
        }
        return newsDTO;
    }
}
