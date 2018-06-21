package kz.epam.intlab.converter;

import kz.epam.intlab.dto.DTOModel;
import kz.epam.intlab.entity.Comment;
import kz.epam.intlab.entity.News;
import org.springframework.stereotype.Component;

@Component("newsConverter")
public class NewsConverterImpl implements DTOEntityConverter<News> {

    @Override
    public News convertDTOToEntity(DTOModel model) {

        News news = new News();
        news.setId(model.getId());
        news.setDate(model.getDate());
        news.setTitle(model.getTitle());
        news.setBrief(model.getBrief());
        news.setContent(model.getContent());

        return news;
    }

    @Override
    public DTOModel convertEntityToDTO(News news) {

        DTOModel model = new DTOModel();
        model.setId(news.getId());
        model.setDate(news.getDate());
        model.setTitle(news.getTitle());
        model.setBrief(news.getBrief());
        model.setContent(news.getContent());

        for(Comment comment: news.getCommentList()) {
            model.getDTOCommentList().add(comment);
        }
        return model;
    }
}
