package com.example.ms_account.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "moderators")
public class Moderator {

    @Column(name = "moderatorUserId")
    private long moderatorUserId;

    @Column(name = "moderatorComId")
    private long moderatorComId;

    public Moderator() {
    }

    public Moderator(long moderatorUserId, long moderatorComId) {
        this.moderatorUserId = moderatorUserId;
        this.moderatorComId = moderatorComId;
    }

    

}
