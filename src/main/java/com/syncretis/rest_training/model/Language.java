package com.syncretis.rest_training.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(
        name = "languages",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_language_name",
                columnNames = {"name"}
        )
)
@ToString
public class Language {
    @DecimalMin("1")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToMany
    @JoinTable(
            name = "person_to_language",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;

    public Language(String name) {
        this.name = name;
    }
}