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
@Getter 
@Setter 
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Community name")
    private String communityName;

    @Column(name = "Description")
    private String communityDescription;

    @Column(name = "Community logo")
    private String communityLogo;

    @Column(name = "Community header image")
    private String communityHeaderImage;

    @Column(name = "Created on")
    private Date communityCreatedOnDate;

    @Column(name = "Ammount of members")
    private Integer communityAmmountOfMembers;

    @Column(name = "Ammount of posts")
    private Integer communityAmmountOfPosts;

    @Column(name = "Community creator id")
    private Integer communityCreatorId;

    public Community() {

    }

    public Community(Community newCommunity) {
        this.communityName = newCommunity.getCommunityName();
        this.communityDescription = newCommunity.getCommunityDescription();
        this.communityCreatedOnDate = newCommunity.getCommunityCreatedOnDate();
        this.communityCreatorId = newCommunity.getCommunityCreatorId();
    }
}
