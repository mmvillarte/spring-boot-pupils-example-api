package org.pupils.example.infrastructure.repository;

import org.pupils.example.infrastructure.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
