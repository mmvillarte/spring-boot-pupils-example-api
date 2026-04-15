package org.pupils.example.api.exception;

import com.example.pupilsservice.generated.model.PupilCourseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PupilCourseExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public PupilCourseError handleNullPointerException (
            NullPointerException ex) {

        return new PupilCourseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public PupilCourseError handleIllegalArgumentException (
            IllegalArgumentException ex) {

        return new PupilCourseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public PupilCourseError handleGenericException(Exception ex) {
        return new PupilCourseError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error occurred");
    }
}
