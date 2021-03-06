package com.medicalsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "powiklania_s")
public class Complication extends IdComparableEntity {

    @Id
    @Column(name = "id_powiklania")
    @Getter @Setter
    private int id;

    @Column(name = "nazwa_powiklania", columnDefinition = "varchar(50)")
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "complication", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonManagedReference
    private List<ComplicationDescription> description;

    public Complication() {}

    public Complication(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
