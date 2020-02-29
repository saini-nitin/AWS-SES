package com.cis.constants.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.cis.constants.entity.CandidateStatusEntity;
import com.cis.constants.entity.LocationEntity;
import com.cis.constants.model.LocationModel;
import com.cis.constants.service.CandidateService;
import com.cis.constants.service.LocationService;
//import com.amazonaws.auth;

@CrossOrigin(origins = "*")
@RestController
@EnableAutoConfiguration
@RequestMapping("/cis/constants")
public class ConstantsController {

	@Autowired
	LocationService locationService;
	
	@Autowired
	CandidateService candidateService;
	
	@RequestMapping(value ="/create-location",method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public LocationEntity createLocation(@RequestBody LocationModel locationModel) {
		return locationService.createLocation(locationModel);
	}
	
	@RequestMapping(value ="/search-location/{location}",method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public LocationModel searchLocation(@PathVariable("location") String locationName ) {
		return locationService.findLocationByName(locationName);
	}
	
	@RequestMapping(value ="/location/{id}",method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public Optional<LocationEntity> getLocationById(@PathVariable("id") Long locationId ) {
		return locationService.getLocationById(locationId);
	}
	
	@RequestMapping(value ="/candidateCurrentStatus",method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<CandidateStatusEntity> getAllCandiadteStatus() {
		return candidateService.getAllCandidateStatus();
	}
	
	@RequestMapping(value ="/email",method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public String email() {
		AWSCredentialsProvider awsCreds = new ClasspathPropertiesFileCredentialsProvider();
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(awsCreds)
                .withRegion(Regions.EU_CENTRAL_1) //TODO: make sure you've selected correct region 
                .build();
		SendEmailRequest request = new SendEmailRequest()
				.withDestination(
						new Destination().withToAddresses("saini.nitti@gmail.com"))
				.withMessage(new Message()
						.withBody(new Body()
								.withText(new Content()
										.withCharset("UTF-8").withData("This is a test email from AWS SES service")))
						.withSubject(new Content()
								.withCharset("UTF-8").withData("Ich bin lernen AWS SES")))
				.withSource("saini.nitin.de@gmail.com");
		
		client.sendEmail(request);
		//return candidateService.getAllCandidateStatus();
		return "Email sent";
	}
	
}
