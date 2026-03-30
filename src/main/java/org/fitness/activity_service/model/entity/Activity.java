package org.fitness.activity_service.model.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fitness.activity_service.model.enums.ActivityType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Map;

@Document(collection = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(def = "{'userId': 1, 'createdAt': -1}", name = "user_createdAt_date_idx")
public class Activity {

    @Id
    private String id;

    @NotNull(message = "User ID is required")
    @Indexed
    private String userId;

    @NotBlank(message = "Activity name is required")
    @Size(max = 100, message = "Activity name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Activity type is required (e.g., RUNNING, CYCLING, SWIMMING)")
    private ActivityType type;

    @PositiveOrZero(message = "Duration cannot be negative")
    private long duration;

    @Field("metrics")
    @NotEmpty(message = "Activity metrics cannot be null")
    private Map<String, Object> metrics;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

}
