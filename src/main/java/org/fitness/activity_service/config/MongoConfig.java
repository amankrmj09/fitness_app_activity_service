package org.fitness.activity_service.config;


import org.fitness.activity_service.model.entity.Activity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;

import java.util.UUID;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Bean
    public BeforeConvertCallback<Activity> activityBeforeConvertCallback() {
        return (activity, collection) -> {
            if (activity.getId() == null) {
                activity.setId(UUID.randomUUID().toString());
            }
            return activity;
        };
    }
}
