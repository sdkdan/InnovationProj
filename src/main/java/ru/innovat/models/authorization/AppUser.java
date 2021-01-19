package ru.innovat.models.authorization;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.innovat.models.utils.RolesEnum;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"role", "blocked", "password", "passwordConfirm", "enabled"} )
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String eMail;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne(fetch = FetchType.EAGER)
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
        role.setId_role(RolesEnum.ROLE_USER.id_role);
        role.setRoleName(RolesEnum.ROLE_USER.name());
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
        return blocked == null;
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
        return  (isAccountNonLocked()) ? "Активен" : "Заблокирован";
    }
}
