package org.fitness.activity_service.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.fitness.activity_service.model.enums.ActivityType;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
public class ActivityDTO {

    private String id;

    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    @NotBlank(message = "Activity name cannot be blank")
    private String name;

    @NotBlank(message = "Activity description cannot be blank")
    private String description;

    @NotNull(message = "Activity type cannot be blank")
    private ActivityType type;     // in kilometers (recommended)

    @NotNull(message = "Activity duration cannot be blank")
    private long duration;                      // in seconds (recommended)

    private Instant createdAt;

    @NotEmpty(message = "Activity metrics cannot be blank")
    private Map<String, Object> metrics;
}
