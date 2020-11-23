package ru.innovat.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "email")
    private String eMail;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;
    @Transient
    private String passwordConfirm;
    @Column(name = "enabled")
    private boolean enabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_blocked")
    private Blocked blocked;

    public AppUser() {
        super();
        this.enabled = false;
        Role role = new Role();
        role.setId_role(Roles.Role_User.id_role);
        role.setRoleName(Roles.Role_User.name());
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return (blocked == null);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getStatusToString() {
        if (isAccountNonLocked()) {
            return "Активен";
        } else return "Заблокирован";
    }
}
enum  Roles{
    Role_Admin(1),
    Role_User(2),
    Role_Support(3);

    int id_role;
    Roles(int id_role) {
        this.id_role = id_role;
    }
}
