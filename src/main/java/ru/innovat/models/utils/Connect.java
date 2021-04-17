package ru.innovat.models.utils;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Connect {

    private int event_Id;
    private int project_Id;
    private int organization_Id;
    private int typeEvent_id;
    private int person_id;
    private int role_id;
}
