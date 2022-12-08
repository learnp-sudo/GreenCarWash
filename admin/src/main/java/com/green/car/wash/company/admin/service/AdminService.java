package com.green.car.wash.company.admin.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.green.car.wash.company.admin.WrapperModel.WasherRatings;
import com.green.car.wash.company.admin.model.OrderDetails;
import com.green.car.wash.company.admin.model.Ratings;
import com.green.car.wash.company.admin.model.WasherProfile;
import com.green.car.wash.company.admin.model.customerDetails;


@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;

    //Url to access the methods of Order Service
    String url="http://ORDER/orders";
    //Url to access the methods of washer Service
    String url1="http://WASHER/washers";
    //Url to access the methods of customer Service
    String url3="http://CUSTOMER/customer";


  //To see all the Orders
    public List<OrderDetails> getAllOrders(){
        OrderDetails[] orders=restTemplate.getForObject(url+"/findall",OrderDetails[].class);
        return (Arrays.asList(orders));
    }
    /** Order controls through admin using rest template*/
    //To assign a washer to the order by Admin
    public OrderDetails assignWasher(OrderDetails orderDetails){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<OrderDetails> assignedWasher = new HttpEntity<>(orderDetails,headers);
        return restTemplate.exchange(url+"/assignWasher", HttpMethod.PUT,assignedWasher,OrderDetails.class).getBody();
    }
    //To see all washers
    public List<WasherProfile> getAllWashers(){
        WasherProfile[] washers=restTemplate.getForObject(url1+"/getWashers",WasherProfile[].class);
        return (Arrays.asList(washers));
    }
    //To get the details of Washers with all their reviews
    public WasherRatings washerSpecificRatings(String fullName){
        //Using a wrapper-class here to get 2 json in one
    	System.out.println(fullName);
        WasherProfile wd =restTemplate.getForObject(url1+"/Washer/"+fullName,WasherProfile.class);
        System.out.println(wd.getFullName());
        Ratings[] ratingsList=restTemplate.getForObject(url3+"/washerSpecificRating/"+fullName,Ratings[].class);
        //Wrapping into a "Proxy class"
        return  new WasherRatings(wd.getFullName(),wd.getEmail(),Arrays.asList(ratingsList));
    }
	public List<WasherProfile> getOneWasher(String fullName) {
		WasherProfile[] wp=restTemplate.getForObject(url1+"/Washer/"+fullName,WasherProfile[].class);
		return (Arrays.asList(wp));
	}

}