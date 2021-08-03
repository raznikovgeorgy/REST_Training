package com.syncretis.rest_training.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language that = (Language) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result *= 37 + Objects.hashCode(id);
        result *= 37 + Objects.hashCode(name);
        return result;
    }
}