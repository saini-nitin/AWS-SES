package com.cis.constants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cis.constants.entity.LocationEntity;


public interface LocationsRepository extends MongoRepository<LocationEntity, Long>{
	LocationEntity findFirstByLocationName(String locationName);
}
