package ru.innovat.models.authorization;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blocked")
@Getter
@Setter
public class Blocked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_blocked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private AppUser appUser;
    @Column(name = "enddate")
    private String endDate;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "comment")
    private String comment;
}

