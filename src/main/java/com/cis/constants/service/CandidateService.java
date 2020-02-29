package com.cis.constants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cis.constants.entity.CandidateStatusEntity;
import com.cis.constants.repository.CandidateStatusRepository;

@Service
public class CandidateService {
	
	@Autowired
	CandidateStatusRepository candidateStatusRepository;
	
	public List<CandidateStatusEntity> getAllCandidateStatus() {
		return candidateStatusRepository.findAll();
	}

}
