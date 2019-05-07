package ru.innovat.models.utils;

public class PersonConnect { //вспомогательный класс для выпадающих списков
    private int event_Id;
    private int project_Id;
    private int organization_Id;

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
