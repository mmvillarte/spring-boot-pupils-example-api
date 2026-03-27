package org.pupils.example.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "COURSE", schema = "PUPIL_COURSE_SCHEMA")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COURSE_NAME", nullable = false)
    private String courseName;

}
