package ru.innovat.models.utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "type_event")
@Getter
@Setter
public class TypeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_type_event;
    @Column(name = "Name_type_event")
    private String nameTypeEvent;
}