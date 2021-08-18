package com.syncretis.rest_training.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_users_id",
                        columnNames = {"id"}),
                @UniqueConstraint(
                        name = "uk_users_name",
                        columnNames = {"name"})
        })
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "city")
    private String city;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
}