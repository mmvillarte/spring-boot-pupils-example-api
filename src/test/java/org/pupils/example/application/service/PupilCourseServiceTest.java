package org.pupils.example.application.service;


import com.example.pupilsservice.generated.model.PupilCourseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pupils.example.infrastructure.entity.Course;
import org.pupils.example.infrastructure.entity.Pupil;
import org.pupils.example.infrastructure.entity.PupilCourse;
import org.pupils.example.infrastructure.model.CourseStatus;
import org.pupils.example.infrastructure.repository.PupilCourseRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension .class)
class PupilCourseServiceTest {
    @Mock
    private PupilCourseRepository repository;

    @InjectMocks
    private PupilCourseService service;

    private PupilCourse pupilCourse;

    @BeforeEach
    void setUp() {
        Pupil pupil = new Pupil();
        pupil.setFirstName("John");
        pupil.setLastName("Doe");

        Course course = new Course();
        course.setCourseName("Spring Boot");

        pupilCourse = new PupilCourse();
        pupilCourse.setPupil(pupil);
        pupilCourse.setCourse(course);
        pupilCourse.setStatus(CourseStatus.IN_PROGRESS);
    }

    @Test
    void shouldReturnMappedPupilCourseResponse_whenDataExists() {
        // given
        when(repository.findPupilAndCourseByName("Doe, John", "Spring Boot", PupilCourseService.sort))
                .thenReturn(List.of(pupilCourse));

        // when
        List<PupilCourseResponse> result =
                service.getPupilsWithCoursesByName("Doe, John", "Spring Boot");

        // then
        assertThat(result).hasSize(1);

        PupilCourseResponse response = result.get(0);
        assertThat(response.getPupil()).isEqualTo("Doe, John");
        assertThat(response.getCourseName()).isEqualTo("Spring Boot");
        assertThat(response.getCourseStatus()).isEqualTo("IN_PROGRESS");

        verify(repository, times(1))
                .findPupilAndCourseByName("Doe, John", "Spring Boot", PupilCourseService.sort);
    }

    @Test
    void shouldReturnEmptyList_whenNoResultsFound() {
        // given
        when(repository.findPupilAndCourseByName(any(), any(), Mockito.any()))
                .thenReturn(List.of());

        // when
        List<PupilCourseResponse> result =
                service.getPupilsWithCoursesByName("Unknown", "Unknown");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldCallRepositoryEvenWithNullParameters() {
        // given
        when(repository.findPupilAndCourseByName(null, null, PupilCourseService.sort))
                .thenReturn(List.of());

        // when
        service.getPupilsWithCoursesByName(null, null);

        // then
        verify(repository).findPupilAndCourseByName(null, null, PupilCourseService.sort);
    }
}