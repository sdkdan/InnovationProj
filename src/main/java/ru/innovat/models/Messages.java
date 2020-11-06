package ru.innovat.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_message;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private AppUser appUser;

    @Column(name = "time")
    private Date time;

    @Column(name = "user_message")
    private Boolean user_message;

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getUser_message() {
        return user_message;
    }

    public void setUser_message(Boolean user_message) {
        this.user_message = user_message;
    }

    public Messages(String message, AppUser appUser, Date time, Boolean user_message) {
        this.message = message;
        this.appUser = appUser;
        this.time = time;
        this.user_message = user_message;
    }
    public Messages() {
    }
}
