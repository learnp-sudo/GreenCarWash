package com.example.shopping.WalletServiceManagemnet.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.WalletServiceManagemnet.Models.Ewallet;
@Repository
public interface EwalletRepository extends MongoRepository<Ewallet,Integer>{

	 public Ewallet findByProfileId(int profileId);
}
