package com.green.car.wash.company.cart.model;

import java.util.List;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="cart")
public class Cart
{

@Id
private int cartId;
private List<Items> items;
private int totalPrice;
private List<customerDetails1> CustomerDetails;
public List<customerDetails1> getCustomerDetails() {
	return CustomerDetails;
}
public void setCustomerDetails(List<customerDetails1> customerDetails) {
	CustomerDetails = customerDetails;
}
public int getCartId() {
	return cartId;
}
public void setCartId(int cartId) {
	this.cartId = cartId;
}
public List<Items> getItems() {
	return items;
}
public void setItems(List<Items> items) {
	this.items = items;
}
public int getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
}





}
