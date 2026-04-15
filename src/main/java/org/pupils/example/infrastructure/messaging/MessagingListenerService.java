package org.pupils.example.infrastructure.messaging;

import com.example.pupilsservice.generated.model.PupilCourseUpdate;
import org.pupils.example.application.service.PupilCourseService;
import org.pupils.example.infrastructure.config.PupilCourseRabbitMQBeanConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessagingListenerService {
    private final PupilCourseService pupilCourseService;

    public MessagingListenerService(PupilCourseService pupilCourseService) {
        this.pupilCourseService = pupilCourseService;
    }

    @RabbitListener(queues = PupilCourseRabbitMQBeanConfig.RABBITMQ_QUEUE)
    public void consume(PupilCourseUpdate pupilCourseUpdate) {
        pupilCourseService.updatePupilCourse(pupilCourseUpdate);
    }
}
