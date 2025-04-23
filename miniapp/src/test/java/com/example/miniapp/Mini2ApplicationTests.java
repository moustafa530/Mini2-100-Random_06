package com.example.miniapp;

import org.springframework.beans.factory.annotation.Value;
import com.example.miniapp.models.*;
import com.example.miniapp.repositories.*;
import com.example.miniapp.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

		// Drop tables if they exist — order matters due to FK constraints

		// Create captains table


		// Create customers table


		// Create trips table — note FK references captains and customers


		// Create payments table — FK to trips



class Mini2ApplicationTests {

	@Autowired
	private RestTemplate restTemplate;


	@Autowired
	private RatingService ratingService;
	@Autowired
	private RatingRepository ratingRepository;


	private final String BASE_URL_RATING = "http://localhost:8080/rating";


	@Value("${ModelsPath.Rating}")
	String RatingPath;


	@BeforeEach
	public void setup() {
		// Ensure all records are deleted

		// paymentRepository.deleteAll();
		// tripRepository.deleteAll();
		ratingRepository.deleteAll();
		// captainRepository.deleteAll();
		// customerRepository.deleteAll();
	}

	public static Field findFieldIgnoreCase(Class<?> clazz, String fieldName) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equalsIgnoreCase(fieldName)) {
				return field;
			}
		}
		return null; // Field not found
	}

	Field getID(String ClassPath) throws ClassNotFoundException, NoSuchFieldException {
		Field ID = findFieldIgnoreCase(Class.forName(ClassPath), "id");
		ID.setAccessible(true);
		return ID;
	}

	@Test
	void contextLoads() {

	}


	@Test
	public void testControllerFindRatingsByEntityNotFound() {
		ResponseEntity<List> response = restTemplate.getForEntity(
				BASE_URL_RATING + "/findByEntity?entityId=999&entityType=captain",
				List.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().isEmpty());
	}

	@Test
	public void testControllerFindRatingsAboveScoreNoneFound() {
		ratingService.addRating(new Rating(7L, "customer", 2, "Bad.", LocalDateTime.now()));
		ResponseEntity<List> response = restTemplate.getForEntity(
				BASE_URL_RATING + "/findAboveScore?minScore=5",
				List.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().isEmpty());
	}


	@Test
	public void testControllerAddRating() {
		Rating newRating = new Rating(1L, "customer", 5, "Excellent service!", LocalDateTime.now());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Rating> request = new HttpEntity<>(newRating, headers);

		ResponseEntity<Rating> response = restTemplate.postForEntity(BASE_URL_RATING + "/addRating", request, Rating.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(newRating.getScore(), response.getBody().getScore());
	}

	@Test
	public void testControllerUpdateRating() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Rating rating = new Rating(2L, "customer", 4, "Good service.", LocalDateTime.now());
		ratingService.addRating(rating);

		rating.setComment("Updated service.");
		rating.setScore(5);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Rating> request = new HttpEntity<>(rating, headers);

		ResponseEntity<Rating> response = restTemplate.exchange(
				BASE_URL_RATING + "/update/" + (getID(RatingPath).get(rating)),
				HttpMethod.PUT,
				request,
				Rating.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(5, response.getBody().getScore());
	}

	@Test
	public void testControllerDeleteRating() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
		Rating rating = new Rating(3L, "captain", 3, "Average captain.", LocalDateTime.now());
		ratingService.addRating(rating);

		ResponseEntity<String> response = restTemplate.exchange(
				BASE_URL_RATING + "/delete/" + getID(RatingPath).get(rating),
				HttpMethod.DELETE,
				null,
				String.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testControllerFindRatingsByEntity() {
		Rating rating1 = new Rating(4L, "trip", 5, "Excellent trip!", LocalDateTime.now());
		Rating rating2 = new Rating(4L, "trip", 4, "Good trip!", LocalDateTime.now());
		ratingService.addRating(rating1);
		ratingService.addRating(rating2);

		ResponseEntity<List> response = restTemplate.getForEntity(
				BASE_URL_RATING + "/findByEntity?entityId=4&entityType=trip",
				List.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testControllerFindRatingsByNonExistingEntity() {
		ResponseEntity<List> response = restTemplate.getForEntity(
				BASE_URL_RATING + "/findByEntity?entityId=4&entityType=trip",
				List.class
		);

		assertEquals(response.getBody().size(), 0);
	}

	@Test
	public void testControllerFindRatingsAboveScore() {
		Rating rating1 = new Rating(5L, "customer", 3, "Okay service.", LocalDateTime.now());
		Rating rating2 = new Rating(6L, "customer", 5, "Awesome service.", LocalDateTime.now());
		ratingService.addRating(rating1);
		ratingService.addRating(rating2);

		ResponseEntity<List> response = restTemplate.getForEntity(
				BASE_URL_RATING + "/findAboveScore?minScore=4",
				List.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}



}