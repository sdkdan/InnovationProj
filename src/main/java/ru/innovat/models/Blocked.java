package ru.innovat.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blocked")
public class Blocked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_block;
    @Basic
    @Column(name = "unban_date")
    private Date unbanDate;
    @Basic
    @Column(name = "comment")
    private String comment;
    @OneToOne(mappedBy = "blocked")
    private AppUser appUser;
}
