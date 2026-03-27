package org.pupils.example.infrastructure.repository;

import org.pupils.example.infrastructure.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PupilRepository extends JpaRepository<Pupil, Long> {
}
