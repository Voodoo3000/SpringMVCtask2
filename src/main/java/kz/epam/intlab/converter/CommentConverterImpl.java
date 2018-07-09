package kz.epam.intlab.converter;

import kz.epam.intlab.dto.CommentDTO;
import kz.epam.intlab.entity.Comment;

import javax.ejb.Stateless;

@Stateless
public class CommentConverterImpl implements DTOEntityConverter<Comment, CommentDTO> {

    @Override
    public Comment convertDTOToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setNewsId(commentDTO.getNewsId());
        comment.setCommentAuthor(commentDTO.getCommentAuthor());
        comment.setCommentDate(commentDTO.getCommentDate());
        comment.setCommentContent(commentDTO.getCommentContent());
        return comment;
    }

    @Override
    public CommentDTO convertEntityToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getId());
        commentDTO.setNewsId(comment.getNewsId());
        commentDTO.setCommentDate(comment.getCommentDate());
        commentDTO.setCommentAuthor(comment.getCommentAuthor());
        commentDTO.setCommentContent(comment.getCommentContent());
        return commentDTO;
    }
}
