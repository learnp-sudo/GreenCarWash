package com.example.shopping.WalletServiceManagemnet.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.WalletServiceManagemnet.Models.Ewallet;
import com.example.shopping.WalletServiceManagemnet.Models.Statement;
import com.example.shopping.WalletServiceManagemnet.Models.TransactionType;
import com.example.shopping.WalletServiceManagemnet.Repository.EwalletRepository;
import com.example.shopping.WalletServiceManagemnet.Repository.StatementRepository;

@Service
public class EwalletServiceImpl implements EwalletService{
     @Autowired
	EwalletRepository ewalletRepository;
	
     @Autowired
       StatementRepository statementRepository;
	@Override
	public List<Ewallet> getWallets() {
		
		return ewalletRepository.findAll();
	}

	@Override
	public Ewallet addWallet(Ewallet ewallet) {
		// TODO Auto-generated method stub
		return ewalletRepository.insert(ewallet);
	}

	@Override
	public void addMoney(int profileId, double money) {
		
		Ewallet ewallet=ewalletRepository.findByProfileId(profileId);
		double totalBalance=ewallet.getCurrentBalance()+money;
		  ewallet.setCurrentBalance(totalBalance);
		  ewalletRepository.save(ewallet);
		  Statement statement=new Statement(profileId,TransactionType.CREDIT,money,LocalDateTime.of(LocalDate.now(),LocalTime.now()),ewallet);
		  statementRepository.save(statement);
	}

	/*@Override
	public void updateWallet(Ewallet ewallet, double amount, String statement, int id) {
		
		
	}*/

	@Override
	public Ewallet getWalletById(int id) {
		
		return ewalletRepository.findByProfileId(id);
	}

	@Override
	public List<Statement> getStatementById(int statementId) {
		
		return statementRepository.findByStatementId(statementId);
	}

	@Override
	public List<Statement> getAllStatements() {
		
		return statementRepository.findAll();
	}

	@Override
	public void deleteWalletById(int id) {

   		  Ewallet ewallet=ewalletRepository.findByProfileId(id);
   		  ewallet.setCurrentBalance(0);
   		  ewalletRepository.save(ewallet);
   		  ewalletRepository.delete(ewallet);
	}

}
