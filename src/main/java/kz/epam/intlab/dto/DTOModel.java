package kz.epam.intlab.dto;

import kz.epam.intlab.entity.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DTOModel {

    private int id;
    private String title;
    private String brief;
    private String content;
    private String date = String.valueOf(new Date());

    private int commentId;
    private String commentContent;
    private String commentDate = String.valueOf(new Date());
    private String commentAuthor;

    private List<Comment> DTOCommentList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public List<Comment> getDTOCommentList() {
        return DTOCommentList;
    }

    public void setDTOCommentList(List<Comment> DTOCommentList) {
        this.DTOCommentList = DTOCommentList;
    }
}
