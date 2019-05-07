package ru.innovat.models.utils;
import javax.persistence.*;

@Entity
@Table(name = "type_event", schema = "", catalog = "x92176f5_inovat")
public class TypeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_type_event;

    @Basic
    @Column(name = "Name_type_event")
    private String name_type_event;

    public int getId_type_event() {
        return id_type_event;
    }

    public void setId_type_event(int id_type_event) {
        this.id_type_event = id_type_event;
    }

    public String getName_type_event() {
        return name_type_event;
    }

    public void setName_type_event(String name_type_event) {
        this.name_type_event = name_type_event;
    }



//    public Set<Event> getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(Set<Event> attachments) {
//        this.attachments = attachments;
//    }

//    @OneToMany(mappedBy = "type_event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Event> attachments = new HashSet<>();

}