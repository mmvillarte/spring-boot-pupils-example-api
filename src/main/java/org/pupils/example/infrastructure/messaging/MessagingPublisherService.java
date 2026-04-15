package org.pupils.example.infrastructure.messaging;

import com.example.pupilsservice.generated.model.PupilCourseUpdate;
import org.pupils.example.infrastructure.config.PupilCourseRabbitMQBeanConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingPublisherService {
    private final RabbitTemplate rabbitTemplate;

    public MessagingPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(PupilCourseUpdate pupilCourseUpdate) {
        rabbitTemplate.convertAndSend(
                PupilCourseRabbitMQBeanConfig.RABBITMQ_EXCHANGE,
                PupilCourseRabbitMQBeanConfig.RABBITMQ_ROUTING_KEY,
                pupilCourseUpdate
        );
    }
}
