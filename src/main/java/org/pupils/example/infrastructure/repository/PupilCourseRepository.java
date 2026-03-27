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

}
