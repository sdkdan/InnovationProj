package ru.innovat.models;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user_role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private AppUser id_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role id_role;

    public int getId_user_role() {
        return id_user_role;
    }

    public void setId_user_role(int id_user_role) {
        this.id_user_role = id_user_role;
    }

    public AppUser getId_user() {
        return id_user;
    }

    public void setId_user(AppUser id_user) {
        this.id_user = id_user;
    }

    public Role getId_role() {
        return id_role;
    }

    public void setId_role(Role id_role) {
        this.id_role = id_role;
    }
}