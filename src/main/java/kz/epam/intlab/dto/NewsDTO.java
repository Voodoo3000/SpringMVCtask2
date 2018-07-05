package kz.epam.intlab.dto;

import kz.epam.intlab.entity.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDTO extends ParentDTO {

    private int id;
    private String title;
    private String brief;
    private String content;
    private String date = String.valueOf(new Date());
    private List<Comment> DTOCommentList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Comment> getDTOCommentList() {
        return DTOCommentList;
    }

    public void setDTOCommentList(List<Comment> DTOCommentList) {
        this.DTOCommentList = DTOCommentList;
    }
}
