package ru.innovat.models.utils;

public enum RolesEnum {

    ROLE_ADMIN(1),
    ROLE_USER(2),
    ROLE_SUPPORT(3);

    public final int id_role;

    RolesEnum(int id_role) {
        this.id_role = id_role;
    }
}
