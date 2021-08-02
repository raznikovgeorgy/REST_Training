package com.syncretis.rest_training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(
        name = "persons"
)
@NoArgsConstructor
public class Person {
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", department=" + department +
                ", document=" + document +
                '}';
    }

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "surname", nullable = false)
    @NotNull(message = "Surname cannot be null")
    private String surname;

    @Column(name = "birthday", nullable = false)
    @NotNull(message = "Birthday cannot be null")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Department ID cannot be null")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "person_to_language",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;

    public Person(String name, String surname, LocalDate birthday,
                  Department department, List<Language> languages, Document document) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.department = department;
        this.languages = languages;
        this.document = document;
    }

    public Person(long id, String name, String surname, LocalDate birthday,
                  Department department, List<Language> languages, Document document) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.department = department;
        this.languages = languages;
        this.document = document;
    }
}