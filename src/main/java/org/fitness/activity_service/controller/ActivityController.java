package org.fitness.activity_service.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fitness.activity_service.model.dto.ActivityDTO;
import org.fitness.activity_service.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO activityDTO) {
        ActivityDTO createdActivity = activityService.saveActivity(activityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }
}
