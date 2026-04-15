package org.pupils.example.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PupilCourseRabbitMQBeanConfig {
    public static final String RABBITMQ_EXCHANGE = "pupil.course.exchange";
    public static final String  RABBITMQ_QUEUE = "pupil.course.status.queue";
    public static final String RABBITMQ_ROUTING_KEY = "pupil.course.status.update";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RABBITMQ_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(RABBITMQ_QUEUE).build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(directExchange())
                .with(RABBITMQ_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
