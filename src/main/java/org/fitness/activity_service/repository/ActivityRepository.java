package org.fitness.activity_service.repository;

import org.fitness.activity_service.model.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {

}
