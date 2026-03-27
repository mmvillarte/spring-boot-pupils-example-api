package org.pupils.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PUPIL", schema = "PUPIL_COURSE_SCHEMA")
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PUPIL_FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "PUPIL_LASTNAME", nullable = false)
    private String lastName;

}
