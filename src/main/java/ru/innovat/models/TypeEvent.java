//package ru.innovat.models;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "type_event")
//public class TypeEvent {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id_type_event;
//    @Column(name = "Name_type_event")
//    private String name_type_event;
//
//    public int getId_type_event() {
//        return id_type_event;
//    }
//
//    public void setId_type_event(int id_type_event) {
//        this.id_type_event = id_type_event;
//    }
//
//    public String getName_type_event() {
//        return name_type_event;
//    }
//
//    public void setName_type_event(String name_type_event) {
//        this.name_type_event = name_type_event;
//    }
//
//    @OneToMany(mappedBy = "type_event", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Event> eventList;
//
//    public TypeEvent(){
//
//    }
//    public void getEvent(){
//
//    }
//
//    public void addEvent(Event event) {
//        event.setTypeEvent(this);
//        eventList.add(event);
//    }
//
//    public Set<Event> getEventList() {
//        return eventList;
//    }
//
//    public void setEventList(Set<Event> eventList) {
//        this.eventList = eventList;
//    }
//}
