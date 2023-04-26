package com.example.ms_post.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name="post")
public class Post {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "postTitle")
    private String postTitle;

    @Column(name="postContent")
    private String postContent;

    @Column(name="postSource")
    private String postSource;
    
    @Column(name="postDate")
    private LocalDate postDate;

    @Column(name="postUpvote")
    private Integer postUpvote;

    @Column(name="postDownvote")
    private Integer postDownvote;

    @Column(name="postIdUser")
    private long postIdUser;

    @Column(name="postIdCom")
    private Integer postIdCom;

    public Post() {
        
    }

    public Post(String postTitle, String postContent, String postSource, LocalDate postDate, long postIdUser, Integer postIdCom) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postSource = postSource;
        this.postDate = postDate;
        this.postUpvote = 0;
        this.postDownvote = 0;
        this.postIdUser = postIdUser;
        this.postIdCom = postIdCom;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", postTitle=" + postTitle + ", postContent=" + postContent + ", postSource="
                + postSource + ", postDate=" + postDate + ", postUpvote=" + postUpvote + ", postDownvote="
                + postDownvote + ", postIdUser=" + postIdUser + ", postIdCom=" + postIdCom + "]";
    }

   
  
    
}
