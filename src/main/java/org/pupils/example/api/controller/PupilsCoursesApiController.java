package org.pupils.example.api.controller;

import com.example.pupilsservice.generated.api.PupilsCoursesApi;
import com.example.pupilsservice.generated.model.PupilCourseResponse;
import org.pupils.example.application.service.PupilCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PupilsCoursesApiController implements PupilsCoursesApi {

    private final PupilCourseService service;

    public PupilsCoursesApiController(PupilCourseService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<PupilCourseResponse>> _getPupilsCourses() {
        return ResponseEntity.ok(service.getAllPupilsWithCourses());
    }
}
