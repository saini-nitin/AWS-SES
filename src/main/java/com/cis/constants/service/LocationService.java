package com.cis.constants.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cis.constants.entity.LocationEntity;
import com.cis.constants.mappers.Mapper;
import com.cis.constants.model.LocationModel;
import com.cis.constants.repository.LocationsRepository;

@Service
public class LocationService {

	@Autowired
	LocationsRepository locationsRepository;
	
	private static  Mapper mapper = new Mapper();
	
	private static final Logger LOG = LoggerFactory.getLogger(LocationService.class);
	
	public LocationEntity createLocation(LocationModel locationModel) {
		LocationEntity locationEntity = new LocationEntity();
		if(locationModel!=null) {
			if(locationModel.getLocationId()!=null) {
				locationEntity.setLocationId(locationModel.getLocationId());
			}
			locationEntity.setLocationName(locationModel.getLocationName());
			return locationsRepository.insert(locationEntity);
		}
		return null;
	}
	
	public LocationModel findLocationByName(String locationName) {
		LocationModel locationModel = new LocationModel();
		locationModel = mapper.mapLocationEntityToModelLocation(locationsRepository.findFirstByLocationName(locationName), locationModel);
		return locationModel;
	}
	
	public Optional<LocationEntity> getLocationById(Long locationId) {
		return locationsRepository.findById(locationId);
	}
}
