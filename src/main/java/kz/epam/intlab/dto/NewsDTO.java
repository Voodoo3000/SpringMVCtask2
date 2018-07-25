package kz.epam.intlab.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class NewsDTO extends ParentDTO {

    private int id;
    private String title;
    private String brief;
    private String content;
    private String date = String.valueOf(new Date());
    private List<CommentDTO> DTOCommentList = new ArrayList<>();

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

    public List<CommentDTO> getDTOCommentList() {
        return DTOCommentList;
    }

    public void setDTOCommentList(List<CommentDTO> DTOCommentList) {
        this.DTOCommentList = DTOCommentList;
    }
}
