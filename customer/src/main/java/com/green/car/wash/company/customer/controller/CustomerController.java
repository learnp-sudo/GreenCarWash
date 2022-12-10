package com.green.car.wash.company.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.car.wash.company.customer.models.OrderDetails;
import com.green.car.wash.company.customer.models.Ratings;
import com.green.car.wash.company.customer.models.WashPacks;
import com.green.car.wash.company.customer.models.customerDetails;
import com.green.car.wash.company.customer.repository.CustomerRepo;
import com.green.car.wash.company.customer.service.CustomerService;
import com.green.car.wash.company.customer.service.OrderService;
import com.green.car.wash.company.customer.service.RatingsService;
import com.green.car.wash.company.customer.wrapperclass.OrderReceipt;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	Logger log = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerRepo customerrepo;
	@Autowired
	private RatingsService ratingservice;
	@Autowired
	private CustomerService customerservice;
	@Autowired
	private OrderService orderservice;
    //add ratings
	@PostMapping("/addRating")
    public Ratings addRating(@RequestBody Ratings ratings){
        return ratingservice.addRating(ratings);
    }
	//get all ratings
	@GetMapping("/getallRatings")
    public List<Ratings> getallratings(){
		log.info("all the ratings");
        return ratingservice.getallRatings();
    }
	//get the washer specific ratings
	@GetMapping("/washerSpecificRating/{washerName}")
    public List<Ratings> washerSpecificRating(@PathVariable String washerName){
        log.info("washerDetails");
		return ratingservice.washerSpecific(washerName);
	}
	//adding details of customer
    @PostMapping("/addDetails")
    public customerDetails addDetails(@RequestBody customerDetails details)
    {

    	return customerservice.addDetails(details);
    }
    //update the customer details
    @PutMapping("/update/{Id}")
    public ResponseEntity updateDetails(@PathVariable String Id ,@RequestBody customerDetails details)
    {
    	customerservice.updateDetails(details);
    	return ResponseEntity.ok().build();
    }
    //delete customer details by id
	    @DeleteMapping("/delete/{id}")
	    public void deleteproduct(@PathVariable String id)
	    {
	        customerrepo.deleteById(id);

	    }
    //get all details of customer by id
    @GetMapping("/{id}")
    public List<customerDetails> customerSpecific(@PathVariable String id){
        return customerservice.CustomerSpecific(id);
	}
  //To see all the wash packs
    @GetMapping("/seeWP")
    public List<WashPacks> getAllWP(){
        return orderservice.getAllWP();
    }

    /** Only the methods that call rest-template methods from services are below this comment**/
    //To add an order from User-end
    @PostMapping("/addOrder")
    public OrderDetails addOrder(@RequestBody OrderDetails orderDetails){
        return orderservice.addOrder(orderDetails);
    }
    //To update and order from User-end
    //This won't update the status of order
    @PutMapping("/updateOrder")
    public OrderDetails updateOrder(@RequestBody OrderDetails orderDetails){
        return orderservice.updateOrder(orderDetails);
    }
    //To cancel the Order from user end
    @PutMapping("/cancelOrder")
    public String cancelOrder(@RequestBody OrderDetails orderDetails){
        return orderservice.cancelOrder(orderDetails);
    }
    //To get the receipt of the order after order is completed
    @GetMapping("/getReceipt/{id}")
    public OrderReceipt getReceipt(@PathVariable String id){
       return orderservice.getReceipt(id);
    }
}


