package com.medicalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dane_osobowe")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pacjenta")
    @Getter @Setter
    private int id;

    @Column(name = "imie", columnDefinition = "varchar(50)", nullable = false)
    @Getter @Setter
    private String firstName;

    @Column(name = "nazwisko", columnDefinition = "varchar(50)", nullable = false)
    @Getter @Setter
    private String lastName;

    @Column(name = "plec", columnDefinition = "varchar(1)", nullable = false)
    @Getter @Setter
    private char sex;

    @Column(name = "wiek", nullable = false)
    @Getter @Setter
    private int age;

    @ManyToMany
    @JoinTable(
            name = "choroby_wspolistniejace",
            joinColumns = @JoinColumn(name = "id_pacjenta", referencedColumnName = "id_pacjenta"),
            inverseJoinColumns = @JoinColumn(name = "id_choroby", referencedColumnName = "id_choroby")
    )
    @Getter @Setter
    private List<Disease> diseases;

}