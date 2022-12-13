package com.example.shopping.WalletServiceManagemnet.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="Ewallet")
public class Ewallet {
     @Id
	private String walletId;
	private int profileId;
	private double currentBalance;
	private List<Statement> statement;
	public Ewallet(int profileId,double currentBalance,List<Statement> statements)
	{
		this.profileId=profileId;
		this.currentBalance=currentBalance;
		this.statement=statements;
	}
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public List<Statement> getStatement() {
		return statement;
	}
	public void setStatement(List<Statement> statement) {
		this.statement = statement;
	}

}
