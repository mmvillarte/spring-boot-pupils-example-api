package org.pupils.example.application.service;

import com.example.pupilsservice.generated.model.PupilCourseResponse;
import com.example.pupilsservice.generated.model.PupilCourseUpdate;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.pupils.example.infrastructure.entity.PupilCourse;
import org.pupils.example.infrastructure.model.CourseStatus;
import org.pupils.example.infrastructure.repository.PupilCourseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PupilCourseService {
    private final PupilCourseRepository repository;
    public static final Sort sort = Sort.by(
            Sort.Order.asc("pupil.lastName"),
            Sort.Order.asc("course.courseName"));

    public PupilCourseService(PupilCourseRepository repository) {
        this.repository = repository;
    }

    public List<PupilCourseResponse> getPupilsWithCoursesByName(String fullName, String courseName) {
        return repository.findPupilAndCourseByName(fullName, courseName, sort)
                .stream()
                .map(this::pupilCourseResponse)
                .toList();
    }

    private PupilCourseResponse pupilCourseResponse(PupilCourse pupilCourse) {
        PupilCourseResponse pupilCourseResponse = new PupilCourseResponse();
        pupilCourseResponse.setId(pupilCourse.getId());
        pupilCourseResponse.setPupil(pupilCourse.getPupil().getLastName() + ", " + pupilCourse.getPupil().getFirstName());
        pupilCourseResponse.setCourseName(pupilCourse.getCourse().getCourseName());
        pupilCourseResponse.setCourseStatus(pupilCourse.getStatus().name());

        return pupilCourseResponse;
    }

    @Transactional
    public void updatePupilCourse(PupilCourseUpdate pupilCourseUpdate) {
        if(pupilCourseUpdate == null || pupilCourseUpdate.getId() == null || pupilCourseUpdate.getCourseStatus() == null) {
            throw new IllegalArgumentException("PupilCourseUpdate, id and courseStatus must not be null");
        }

        Optional<PupilCourse> pupilCourseOptional = repository.findById(pupilCourseUpdate.getId());

        if(pupilCourseOptional.isPresent()) {
            PupilCourse pupilCourse = pupilCourseOptional.get();
            pupilCourse.setStatus(CourseStatus.valueOf(pupilCourseUpdate.getCourseStatus()));

            repository.save(pupilCourse);
        } else {
            log.warn("PupilCourse with id {} not found, cannot update course status", pupilCourseUpdate.getId());
            throw new IllegalArgumentException("PupilCourse with id " + pupilCourseUpdate.getId() + " not found, cannot update course status");
        }
    }
}
