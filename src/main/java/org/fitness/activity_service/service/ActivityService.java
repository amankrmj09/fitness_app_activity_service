package org.fitness.activity_service.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fitness.activity_service.model.dto.ActivityDTO;
import org.fitness.activity_service.model.entity.Activity;
import org.fitness.activity_service.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    private final ModelMapper modelMapper;


    public ActivityDTO saveActivity(@Valid ActivityDTO activityDTO) {
        Activity activity = modelMapper.map(activityDTO, Activity.class);
//        activity.setId(UUID.randomUUID());
        Activity savedActivity = activityRepository.save(activity);
        return modelMapper.map(savedActivity, ActivityDTO.class);
    }

    public String deleteActivity(String id){
        activityRepository.deleteById(id);
        return "Activity with id " + id + " has been deleted.";
    }
}
