package com.syncretis.rest_training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(
        name = "documents",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_document_id",
                columnNames = {"id"}
        )
)
public class Document {

    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "expireDate", nullable = false)
    @NotNull(message = "Expire date cannot be null")
    private LocalDate expireDate;

    @OneToOne(mappedBy = "document", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Person person;

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", expireDate=" + expireDate +
                ", pid=" + person.getId() +
                '}';
    }

    public Document(String id, LocalDate expireDate) {
        this.id = id;
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document that = (Document) o;
        return id.equals(that.id) && expireDate.equals(that.expireDate);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result *= 37 + Objects.hashCode(id);
        result *= 37 + Objects.hashCode(expireDate);
        return result;
    }
}