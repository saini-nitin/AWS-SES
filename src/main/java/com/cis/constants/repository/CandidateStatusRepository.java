package com.cis.constants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cis.constants.entity.CandidateStatusEntity;

public interface CandidateStatusRepository extends MongoRepository<CandidateStatusEntity,String>{

}
