package org.pupils.example.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.pupils.example.infrastructure.model.CourseStatus;

@Entity
@Data
@Table(name = "PUPIL_COURSE", schema = "PUPIL_COURSE_SCHEMA")
public class PupilCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PUPIL_COURSE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PUPIL", nullable = false)
    private Pupil pupil;

    @ManyToOne
    @JoinColumn(name = "ID_COURSE", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "STATUS", nullable = false)
    private CourseStatus status;

}
