package ru.innovat.models.utils;

public enum  RolesEnum{
    Role_Admin(1),
    Role_User(2),
    Role_Support(3);

    public int id_role;
    RolesEnum(int id_role) {
        this.id_role = id_role;
    }
}
