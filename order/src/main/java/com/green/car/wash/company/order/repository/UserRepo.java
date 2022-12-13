package com.green.car.wash.company.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.green.car.wash.company.order.model.User1;
public interface UserRepo extends MongoRepository<User1, String> {

	User1 findByUsername(String username);

	Boolean existsByUsername(String username);

}
