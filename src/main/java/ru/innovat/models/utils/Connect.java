package ru.innovat.models.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Connect {
    private int event_Id;
    private int project_Id;
    private int organization_Id;
    private int typeEvent_id;
    private int person_id;
    private int role_id;
}
