package com.example.shopping.WalletServiceManagemnet.Models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Document(collection="statement")
public class Statement {
      @Id
	private int statementId;
	private TransactionType transactionType;
	private double amount;
	private LocalDateTime dateTime;
	private int orderId;
	//private String transactionRemarks;
	private Ewallet ewallet;
	
	public Statement()
	{
		
	}
	public Statement(int statementId,TransactionType transactionType,double amount,LocalDateTime dateTime,Ewallet ewallet)
	{
		this.statementId=statementId;
		this.transactionType=transactionType;
		this.amount=amount;
		this.dateTime=dateTime;
		this.ewallet=ewallet;
	}
}
