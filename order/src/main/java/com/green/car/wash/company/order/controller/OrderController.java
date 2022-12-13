package com.green.car.wash.company.order.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.green.car.wash.company.order.config.JwtUtil;
import com.green.car.wash.company.order.exceptionHandlers.API_requestException;
import com.green.car.wash.company.order.model.AuthenticationRequest;
import com.green.car.wash.company.order.model.AuthenticationResponse;
import com.green.car.wash.company.order.model.Cart;
import com.green.car.wash.company.order.model.MessageResponse;
import com.green.car.wash.company.order.model.OrderDetails;
import com.green.car.wash.company.order.model.User1;
import com.green.car.wash.company.order.repository.OrderRepo;
import com.green.car.wash.company.order.repository.UserRepo;
import com.green.car.wash.company.order.service.MyUserDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepo or;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private UserRepo repo;

    //To get all orders
    @GetMapping("/findall")
    public List<OrderDetails> findallOrders(){
        return or.findAll();
    }
    //Find one object by ID
    @GetMapping("/findone/{orderid}")
    public ResponseEntity<OrderDetails> findoneOrder(@PathVariable String orderid){
        OrderDetails order=or.findById(orderid).orElseThrow(()-> new API_requestException("Order with ID -> "+orderid+" not found"));
         return ResponseEntity.ok(order);
    }
    //To add an order
    @PostMapping("/add/{email}")
    public void addOrder(@RequestBody Cart cart,String email ) {
        OrderDetails order=new OrderDetails();
        order.setUseremailid(cart.getUseremailid());
        order.setWasherName("NA");
        order.setWashpack(cart.getWashpacks());
        order.setPhoneNo(cart.getPhoneNo());
        order.setAreapincode(cart.getAreapincode());
        order.setStatus("Pending");
        order.setCars(cart.getCar());
        or.save(order);
        restTemplate.delete("http://CART/cart/delete/" + cart.getCartId());
    }
    //To delete specific order with id
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Map<String,Boolean>> deleteOrder(@PathVariable String orderId){
        OrderDetails order=or.findById(orderId).orElseThrow(()-> new API_requestException("Order with ID -> "+orderId+" not found,deletion failed"));
        or.delete(order);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Order Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    //To update an order
    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderDetails> updateOrder(@PathVariable String orderId,@RequestBody OrderDetails orderDetails){
        OrderDetails existingOrder=or.findById(orderId).orElseThrow(() -> new API_requestException("Order with ID -> "+orderId+" not found,update failed"));
        //OrderId won't be updated for @putmapping
        //WasherName can't be updated by user
        existingOrder.setWashpack(orderDetails.getWashpack());
        //Status can't be updated by the user
        existingOrder.setCars(orderDetails.getCars());
        existingOrder.setUseremailid(orderDetails.getUseremailid());
        existingOrder.setAreapincode(orderDetails.getAreapincode());
        existingOrder.setPhoneNo(orderDetails.getPhoneNo());
        existingOrder.setCars(orderDetails.getCars());
        OrderDetails updatedOrder=or.save(existingOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    /** Getting consumed by the Washer and Admin model */
    //To filter orders using user emailId
    @GetMapping("/findMyOrders/{useremailid}")
    public List<OrderDetails> getMyOrders(@PathVariable String useremailid){
        return or.findAll().stream().filter(x ->x.getUseremailid().contains(useremailid)).collect(Collectors.toList());
    }
    //To find all the completed orders
    @GetMapping("/findCompleted")
    public List<OrderDetails> getCompletedOrders(){
         return or.findAll().stream().filter(x -> x.getStatus().contains("Completed")).collect(Collectors.toList());
    }
    //To find all the pending orders
    @GetMapping("/findPending")
    public List<OrderDetails> getPendingOrders(){
        return or.findAll().stream().filter(x -> x.getStatus().contains("Pending")).collect(Collectors.toList());
    }
    //To find all the cancelled orders
    @GetMapping("/findCancelled")
    public List<OrderDetails> getCancelledOrders(){
        return or.findAll().stream().filter(x -> x.getStatus().contains("Cancelled")).collect(Collectors.toList());
    }
    //To find all the unassigned orders
    @GetMapping("/findUnassigned")
    public List<OrderDetails> getUnassignedOrders(){
        return or.findAll().stream().filter(x -> x.getWasherName().contains("NA")).collect(Collectors.toList());
    }
    //To fin all the orders by a specific washer
    @GetMapping("/washerSpecficOrder/{washername}")
    public List<OrderDetails> getWasherSpecificOrders(@PathVariable String washername){
        return or.findAll().stream().filter(x -> x.getWasherName().contains(washername)).collect(Collectors.toList());
    }
    //To cancel the order
    @PutMapping("/cancelOrder")
    public String cancelOrder(@RequestBody OrderDetails orderDetails){
        OrderDetails od=or.findById(orderDetails.getOrderId()).get();
        od.setStatus("Cancelled");
        or.save(od);
        return "The order with ID -> "+orderDetails.getOrderId()+" is cancelled successfully";
    }

    /** Methods that are consumed exclusively by rest templates below this comment */
    //This is called by Admin to update the status of the order(For Completed Order)

    @PutMapping("/updateStatus/completed/{orderId}")
    public ResponseEntity<OrderDetails> updateStatus(@PathVariable String orderId){
        OrderDetails existingOrder=or.findById(orderId).orElseThrow(() -> new API_requestException("Order with ID -> "+orderId+" not found, status update failed"));
        existingOrder.setStatus("Completed");
        OrderDetails order=or.save(existingOrder);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/assignWasher")
    public OrderDetails assignWasher(@RequestBody OrderDetails od){
        boolean doesOrderExists=or.existsById(od.getOrderId());
        OrderDetails existingOrder = or.findById(od.getOrderId()).orElse(null);
        if (doesOrderExists && existingOrder.getWasherName().contains("NA")){
            existingOrder.setWasherName(od.getWasherName());
            return or.save(existingOrder);
        }
        else {
            throw new API_requestException("Order not found in database, washer not assigned");
        }
    }
    /*-----------------------------------security----------------------------------*/
	@PostMapping("/reg")
	private ResponseEntity<?> subscribe(@Valid@RequestBody AuthenticationRequest request)
	{
		if (repo.existsByUsername(request.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}


		String username = request.getUsername();
		String password = request.getPassword();

		User1 model = new User1();
		model.setUsername(username);
		model.setPassword(password);
		try {
			repo.save(model);
		}
		catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error while subsribing the user with username " + username));

		}
		    return ResponseEntity.ok(new AuthenticationResponse("client subscribed with username " + username));
	}
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try
		{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}


}
