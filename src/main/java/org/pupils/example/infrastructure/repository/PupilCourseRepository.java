package org.pupils.example.infrastructure.repository;

import org.pupils.example.infrastructure.entity.PupilCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PupilCourseRepository extends JpaRepository<PupilCourse, Long> {

    @Query("""
            SELECT pc
            FROM PupilCourse pc
            JOIN FETCH pc.pupil p
            JOIN FETCH pc.course c
            """)
    List<PupilCourse> findAllWithRelations();

    @Query("""
            SELECT pc
            FROM PupilCourse pc
            JOIN FETCH pc.pupil p
            JOIN FETCH pc.course c
            WHERE (:fullName IS NULL OR
                       LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT(:fullName, '%')))
                  AND (:courseName IS NULL OR
                       LOWER(c.courseName) LIKE LOWER(CONCAT(:courseName, '%')))
            """)
    List<PupilCourse> findPupilAndCourseByName(String fullName, String courseName);
}
