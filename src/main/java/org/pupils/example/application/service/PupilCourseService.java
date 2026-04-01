package org.pupils.example.application.service;

import com.example.pupilsservice.generated.model.PupilCourseResponse;
import org.pupils.example.infrastructure.repository.PupilCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilCourseService {


    private final PupilCourseRepository repository;

    public PupilCourseService(PupilCourseRepository repository) {
        this.repository = repository;
    }

    public List<PupilCourseResponse> getAllPupilsWithCourses() {

        return repository.findAllWithRelations()
                .stream()
                .map(pc -> {
                    PupilCourseResponse dto = new PupilCourseResponse();
                    dto.setPupil(pc.getPupil().getLastName() + ", " + pc.getPupil().getFirstName());
                    dto.setCourseName(pc.getCourse().getCourseName());
                    dto.setCourseStatus(pc.getStatus().name());
                    return dto;
                })
                .toList();
    }

    public List<PupilCourseResponse> getPupilsWithCoursesByName(String fullName, String courseName) {

        return repository.findPupilAndCourseByName(fullName, courseName)
                .stream()
                .map(pc -> {
                    PupilCourseResponse dto = new PupilCourseResponse();
                    dto.setPupil(pc.getPupil().getLastName() + ", " + pc.getPupil().getFirstName());
                    dto.setCourseName(pc.getCourse().getCourseName());
                    dto.setCourseStatus(pc.getStatus().name());
                    return dto;
                })
                .toList();
    }
}
