package com.green.car.wash.company.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.green.car.wash.company.admin.WrapperModel.WasherRatings;
import com.green.car.wash.company.admin.model.*;
import com.green.car.wash.company.admin.service.AdminService;
import com.green.car.wash.company.admin.service.WashPackService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admins")
public class AdminController{
   // @Autowired
   // AdminService as;
    @Autowired
    WashPackService wps;
    @Autowired
    AdminService as;

    /** Washer controls through admin using service object */
    //To find all the washpack
    @GetMapping("/findallWP")
    public List<WashPacks> findallWP(){
        return wps.findallWP();
    }
    //To find one WashPack with ID
    @GetMapping("/findoneWP/{id}")
    public ResponseEntity<WashPacks> findoneWP(@PathVariable String id){
        return wps.findoneWP(id);
    }
    //To add a new WashPack
    @PostMapping("/addWP")
    public WashPacks addWP(@RequestBody WashPacks washPacks){
        return wps.addWP(washPacks);
    }
    //To delete a Washpack
    @DeleteMapping("/deleteWP/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteWP(@PathVariable String id){
        return wps.deleteWP(id);
    }
    //To update a Wash pack
    @PutMapping("/updateWP/{id}")
    public ResponseEntity<WashPacks> updateWP(@PathVariable String id,@RequestBody WashPacks washPacks){
        return wps.updateWP(id,washPacks);
    }
    //To find wash pack with wash pack name for user's receipt
    @GetMapping("/wpbyname/{name}")
    public WashPacks wpbyname(@PathVariable String name){
        return wps.findbyname(name);
    }

    /** Order controls through admin using rest template */
    //To see all the wash orders
    @GetMapping("/orders")
    public List<OrderDetails> getAllOrders(){
        return as.getAllOrders();
    }
    //To assign a washer to the order by Admin

	@PutMapping("/assignWasher")
	public OrderDetails assignWasher(@RequestBody OrderDetails orderDetails) {
		return as.assignWasher(orderDetails);
	}
	@GetMapping("/AllWashers")
	public List<WasherProfile> getAllWashers()
	{
		return as.getAllWashers();
	}


	  //To get all the ratings of a specific Washer

		@GetMapping("/washerRating/{fullName}")
		public WasherRatings washerSpecificRatings(@PathVariable String fullName) {
			return as.washerSpecificRatings(fullName);
		}

	/** Washer controls through admin using rest template */
	// To get one washer

	@GetMapping("/oneWasher/{fullName}")
	public List<WasherProfile> getOneWasher(@PathVariable String fullName) {
		return as.getOneWasher(fullName);
	}

    //To get all the ratings of a specific Washer

	/*
	 * @GetMapping("/washerRating/{name}") public WasherRatings
	 * washerSpecificRatings(@PathVariable String name) { return
	 * as.washerSpecificRatings(name); }
	 */

}

