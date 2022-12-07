package com.green.car.wash.company.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.car.wash.company.customer.models.Ratings;
import com.green.car.wash.company.customer.models.customerDetails;
import com.green.car.wash.company.customer.repository.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	//adding customer profile
	public customerDetails addDetails(customerDetails details){
        return customerRepo.insert(details);
    }
	//deleting customer profile by id
	public String deleteProfile(String id){
        customerRepo.deleteById(id);
        return "ID -> "+id+" deleted successfully";
    }
	//updating the existing customer details
	public void updateDetails(customerDetails details) {
		customerDetails detail =customerRepo.findById(details.getId())
				.orElseThrow(()->new RuntimeException( String.format("cannot find id %s",details.getId())));
		detail.setEmail(details.getEmail());
		detail.setFullName(details.getFullName());
		detail.setPassword(details.getPassword());
		detail.setPhoneNumber(details.getPhoneNumber());
		customerRepo.save(details);
	}
	//method for retrieving customer details according to id
    public List<customerDetails> CustomerSpecific(String id){
		        List<customerDetails> customerDetail=customerRepo.findAll().stream().filter(x -> x.getId().contains(id)).collect(Collectors.toList());
		        return customerDetail;
    }

}
