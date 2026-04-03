package org.fitness.activity_service.service;

import lombok.RequiredArgsConstructor;
import org.fitness.activity_service.client.UserClient;
import org.fitness.activity_service.exception.ResourceNotFoundException;
import org.fitness.activity_service.model.dto.ActivityDTO;
import org.fitness.activity_service.model.entity.Activity;
import org.fitness.activity_service.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    private final ModelMapper modelMapper;

    private final UserClient userClient;

    private final KafkaTemplate<String, Activity> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;


    public ActivityDTO saveActivity(ActivityDTO activityDTO) {

        // Validate user ID using UserClient
        Boolean isValidUser = userClient.validateUser(activityDTO.getUserId());
        if (isValidUser == null || !isValidUser) {
            throw new ResourceNotFoundException("Invalid user ID: " + activityDTO.getUserId());
        }

        Activity activity = modelMapper.map(activityDTO, Activity.class);
//        activity.setId(UUID.randomUUID());
        Activity savedActivity = activityRepository.save(activity);
        try {
            kafkaTemplate.send(topicName, savedActivity.getUserId(),savedActivity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send activity to Kafka: " + e.getMessage());
        }
        return modelMapper.map(savedActivity, ActivityDTO.class);
    }

    public String deleteActivity(String id){
        activityRepository.deleteById(id);
        return "Activity with id " + id + " has been deleted.";
    }
}
