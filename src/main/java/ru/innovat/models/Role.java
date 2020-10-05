package ru.innovat.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "ROLE_UK", columnNames = "Role_Name") })
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_Id", nullable = false)
    private Long roleId;

    @Column(name = "role_Name", length = 30, nullable = false)
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
