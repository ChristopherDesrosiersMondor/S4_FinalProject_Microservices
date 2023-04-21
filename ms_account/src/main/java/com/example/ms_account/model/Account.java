package com.example.ms_account.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "userLastName")
    private String userLastName;

    @Column(name = "userFirstName")
    private String userFirstName;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userPseudo")
    private String userPseudo;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "userBirthday")
    private LocalDate userBirthday;

    public Account() {

    }

    public Account(String userLastName, String userFirstName, String userEmail, String userPseudo,
            String userPassword, LocalDate userBirthday) {
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.userPseudo = userPseudo;
        this.userPassword = userPassword;
        this.userBirthday = userBirthday;
    }


}
