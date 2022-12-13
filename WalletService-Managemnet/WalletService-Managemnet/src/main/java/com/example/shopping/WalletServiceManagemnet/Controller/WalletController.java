package com.example.shopping.WalletServiceManagemnet.Controller;
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.WalletServiceManagemnet.Repository.StatementRepository;
import com.example.shopping.WalletServiceManagemnet.Service.EwalletService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payment")
public class WalletController {

	@Autowired
	  EwalletService ewalletService;
	
	@Autowired
	  StatementRepository stateRepository;
	
	
	@PostMapping("/addMoney/{amount}")
	public String onlinePayment(@PathVariable double amount)throws RazorpayException
	{
		//final PayPalHttpClient payPalHttpClient;
		double amt=amount;
		System.out.println(amt);
		RazorpayClient client=new RazorpayClient("rzp_test_83ojtSGwlrfPSa","FBaxj2jVTymfGn6d5wQSiN5A");
		//payPalHttpClient=new PayPalHttpClient(new PayPalEnvironment.Sandbox("AZMBZ10J32RS_WvLOGsECTpc8FUNTZGM5GvldiTmmoG1ctgWRfroZPQ44-jMj-tSGihrunJkPkfMELwI","EHaEuTAqJLFjEPRVC-Pr9WpSyDvc6tRPXk6Yx_B8SsyyMhfJQkQqY"));
		JSONObject options=new JSONObject();
		
		options.put("amount", amt);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order=client.Orders.create(options);
		System.out.println(order);
		return order.toString();
		
	}
}
