package kz.epam.intlab.converter;

import kz.epam.intlab.dto.DTOModel;
import kz.epam.intlab.entity.Comment;
import org.springframework.stereotype.Component;

@Component("commentConverter")
public class CommentConverterImpl implements DTOEntityConverter<Comment> {

    @Override
    public Comment convertDTOToEntity(DTOModel model) {
        Comment comment = new Comment();
        comment.setNewsId(model.getId());
        comment.setCommentAuthor(model.getCommentAuthor());
        comment.setCommentDate(model.getCommentDate());
        comment.setCommentContent(model.getCommentContent());
        return comment;
    }

    @Override
    public DTOModel convertEntityToDTO(Comment entity) {
        return null;
    }
}
