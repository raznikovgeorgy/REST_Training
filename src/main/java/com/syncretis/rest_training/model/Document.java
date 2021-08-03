package com.syncretis.rest_training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


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

    @OneToOne(mappedBy = "document")
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
}