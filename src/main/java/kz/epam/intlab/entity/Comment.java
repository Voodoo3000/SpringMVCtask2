package kz.epam.intlab.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "NEWS_COMMENT")
public class Comment extends EntityParent {

    private Integer id;
    private Integer newsId;
    private String commentDate;
    private String commentAuthor;
    private String commentContent;

    private News news;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NEWS_ID")
    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    @Column(name = "COMMENT_DATE")
    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Column(name = "AUTHOR")
    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    @Column(name = "COMMENT_CONTENT")
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "NEWS_ID", insertable = false, updatable = false, nullable = false)
    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

}
