package com.example.ms_community.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "community")
@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Community name")
    @Getter 
    @Setter 
    private String communityName;

    @Column(name = "Description")
    @Getter 
    @Setter
    private String communityDescription;

    @Column(name = "Community logo")
    @Getter 
    @Setter
    private String communityLogo;

    @Column(name = "Community header image")
    @Getter 
    @Setter
    private String communityHeaderImage;

    @Column(name = "Created on")
    @Getter 
    @Setter
    private Date communityCreatedOnDate;

    @Column(name = "Ammount of members")
    @Getter 
    @Setter
    private Integer communityAmmountOfMembers;

    @Column(name = "Ammount of posts")
    @Getter 
    @Setter
    private Integer communityAmmountOfPosts;

    @Column(name = "Community creator id")
    @Getter 
    @Setter
    private Integer communityCreatorId;
}
