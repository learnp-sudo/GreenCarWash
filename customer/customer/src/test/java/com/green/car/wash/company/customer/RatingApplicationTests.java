package com.green.car.wash.company.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.green.car.wash.company.customer.models.Ratings;
import com.green.car.wash.company.customer.repository.RatingRepo;
import com.green.car.wash.company.customer.service.RatingsService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RatingApplicationTests {
	@MockBean
	RatingRepo ratingrepo;
	@Autowired
	RatingsService service;

	@Test
	void addRatingTest() {
		Ratings rating = new Ratings("123", "abc", "good", 5);
		when(ratingrepo.save(rating)).thenReturn(rating);
		Ratings res = service.addRating(rating);
		assertEquals(rating,service.addRating(rating));
	}

}
