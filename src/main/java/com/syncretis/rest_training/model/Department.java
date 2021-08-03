package com.syncretis.rest_training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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
    @DecimalMin("1")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
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