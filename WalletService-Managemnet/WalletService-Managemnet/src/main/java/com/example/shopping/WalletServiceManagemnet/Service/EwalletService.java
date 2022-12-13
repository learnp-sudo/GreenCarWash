package com.example.shopping.WalletServiceManagemnet.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopping.WalletServiceManagemnet.Models.Ewallet;
import com.example.shopping.WalletServiceManagemnet.Models.Statement;
@Service
public interface EwalletService {

	public List<Ewallet> getWallets();
	public Ewallet addWallet(Ewallet ewallet);
	public void addMoney(int profileId,double money);
	//public void updateWallet(Ewallet ewallet,double amount,String statement,int id);
	public Ewallet getWalletById(int id);
	public List<Statement> getStatementById(int statementId);
	public List<Statement> getAllStatements();
	public void deleteWalletById(int id);
}
