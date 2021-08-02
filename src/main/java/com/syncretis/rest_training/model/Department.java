package com.syncretis.rest_training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(
        name = "departments",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_department_name",
                columnNames = {"name"}
        )
)
@NoArgsConstructor
public class Department {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "Department Name cannot be null")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Person> persons;

    public Department(String name) {
        this.name = name;
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}