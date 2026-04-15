package org.pupils.example.api.controller;

import com.example.pupilsservice.generated.api.PupilsCoursesApi;
import com.example.pupilsservice.generated.model.PupilCourseResponse;
import com.example.pupilsservice.generated.model.PupilCourseUpdate;
import org.pupils.example.application.service.PupilCourseService;
import org.pupils.example.infrastructure.messaging.MessagingPublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PupilsCoursesApiController implements PupilsCoursesApi {

    private final PupilCourseService service;
    private final MessagingPublisherService messagingPublisherService;

    public PupilsCoursesApiController(PupilCourseService service, MessagingPublisherService messagingPublisherService) {
        this.service = service;
        this.messagingPublisherService = messagingPublisherService;
    }

    @Override
    public ResponseEntity<List<PupilCourseResponse>> _getPupilsCourses() {
        return ResponseEntity.ok(service.getPupilsWithCoursesByName(null, null));
    }

    @Override
    public ResponseEntity<List<PupilCourseResponse>> _getPupilsCoursesByName(String pupilName, String courseName) {
        return ResponseEntity.ok(service.getPupilsWithCoursesByName(pupilName, courseName));
    }

    @Override
    public ResponseEntity<Void> _postPupilCourseUpdate(PupilCourseUpdate pupilCourseUpdate) {
        messagingPublisherService.publish(pupilCourseUpdate);

        return ResponseEntity.ok().build();
    }
}
