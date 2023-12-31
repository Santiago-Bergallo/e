package com.example.eCommerce.v2.model;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time_stamp", nullable = false)
    private Timestamp createdTimeStamp;

    @Lob
    @Column(name = "token", nullable = false)
    private String token;

    @ManyToOne(optional = false)
    @JoinColumn(name = "local_user_id", nullable = false, unique = true)
    private LocalUser localUser;


    public Long getId() {
        return id;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void setLocalUser(LocalUser localUser) {
        this.localUser = localUser;
    }
}