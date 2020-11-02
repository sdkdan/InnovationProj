package ru.innovat.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "blocked")
public class Blocked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_blocked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private AppUser appUser;
    @Basic
    @Column(name = "enddate")
    private String endDate;
    @Column(name = "startdate")
    private Date startDate;
    @Basic
    @Column(name = "comment")
    private String comment;

    public int getId_blocked() {
        return id_blocked;
    }

    public void setId_blocked(int id_blocked) {
        this.id_blocked = id_blocked;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}

