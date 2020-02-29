package com.cis.constants.mappers;

import com.cis.constants.entity.LocationEntity;
import com.cis.constants.model.LocationModel;

public class Mapper {

	
	public LocationModel mapLocationEntityToModelLocation(LocationEntity locationEntity,LocationModel locationModel) {	
		if(locationEntity!=null) {
			locationModel.setLocationId(locationEntity.getLocationId());
			locationModel.setLocationName(locationEntity.getLocationName());
		}
		return locationModel;
	}
}
