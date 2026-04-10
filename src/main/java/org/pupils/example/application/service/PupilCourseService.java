package org.pupils.example.application.service;

import com.example.pupilsservice.generated.model.PupilCourseResponse;
import org.pupils.example.infrastructure.entity.PupilCourse;
import org.pupils.example.infrastructure.repository.PupilCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilCourseService {
    private final PupilCourseRepository repository;

    public PupilCourseService(PupilCourseRepository repository) {
        this.repository = repository;
    }

    public List<PupilCourseResponse> getPupilsWithCoursesByName(String fullName, String courseName) {

        return repository.findPupilAndCourseByName(fullName, courseName)
                .stream()
                .map(this::pupilCourseResponse)
                .toList();
    }

    private PupilCourseResponse pupilCourseResponse(PupilCourse pupilCourse) {
        PupilCourseResponse pupilCourseResponse = new PupilCourseResponse();
        pupilCourseResponse.setPupil(pupilCourse.getPupil().getLastName() + ", " + pupilCourse.getPupil().getFirstName());
        pupilCourseResponse.setCourseName(pupilCourse.getCourse().getCourseName());
        pupilCourseResponse.setCourseStatus(pupilCourse.getStatus().name());

        return pupilCourseResponse;
    }
}
