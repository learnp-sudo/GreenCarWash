package com.green.car.wash.company.washer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.green.car.wash.company.washer.model.WasherProfile;
import com.green.car.wash.company.washer.repository.WasherRepository;

@Service
public class WasherProfileService {
	@Autowired
	private WasherRepository WasherRepo;

	// adding washer profile
	public WasherProfile addDetails(WasherProfile details) {
		return WasherRepo.insert(details);
	}
    // washer can only update his phone number, password and status
	public void updateDetails(WasherProfile details) {
		WasherProfile detail = WasherRepo.findById(details.getEmail())
				.orElseThrow(() -> new RuntimeException(String.format("cannot find emailId %s", details.getEmail())));
		detail.setStatus(details.getStatus());
		detail.setPassword(details.getPassword());
		detail.setPhoneNunmber(details.getPhoneNunmber());
		WasherRepo.save(details);
	}
	//get all washers
	public List<WasherProfile> getAllWashers() {
		return WasherRepo.findAll();
	}
	public void deleteById(String email) {
		WasherRepo.deleteById(email);

	}
	public List<WasherProfile> getWasher(String fullName) {
		return WasherRepo.findAll().stream().filter(x ->x.getFullName().contains(fullName)).collect(Collectors.toList());


	}






}
