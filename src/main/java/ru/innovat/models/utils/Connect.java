package ru.innovat.models.utils;

public class Connect { //вспомогательный класс для выпадающих списков
    private int event_Id;
    private int project_Id;
    private int organization_Id;
    private int typeEvent_id;
    private int person_id;
    private int role_id;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getTypeEvent_id() {
        return typeEvent_id;
    }

    public void setTypeEvent_id(int typeEvent_id) {
        this.typeEvent_id = typeEvent_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getEvent_Id() {
        return event_Id;
    }

    public void setEvent_Id(int event_Id) {
        this.event_Id = event_Id;
    }

    public int getProject_Id() {
        return project_Id;
    }

    public void setProject_Id(int project_Id) {
        this.project_Id = project_Id;
    }

    public int getOrganization_Id() {
        return organization_Id;
    }

    public void setOrganization_Id(int organization_Id) {
        this.organization_Id = organization_Id;
    }
}
