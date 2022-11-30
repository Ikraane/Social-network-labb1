package com.example.Socialnetwork.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class PostDB {

    @Id
    @Column(name = "postID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postID;

    @Column(name = "poster")
    private String poster;

    @Column(name = "message")
    private  String message;

    @Column(name = "date")
    private LocalDateTime date;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime postDate) {
        this.date = postDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID='" + postID + '\'' +
                ", poster='" + poster + '\'' +
                ", message='" + message + '\'' +
                ", postDate=" + date +
                '}';
    }
}
