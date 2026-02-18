package com.epam.jmp.rest;

import jakarta.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.epam.jmp.rest.dto.UserCreatedResponse;
import com.epam.jmp.rest.dto.UserListResponse;
import com.epam.jmp.rest.dto.UserResponse;
import com.epam.jmp.rest.dto.UserUpdateRequest;
import com.epam.jmp.rest.model.UserCommand.UserCreateCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;




@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class RestApplicationTest {


	@Autowired
	private TestRestTemplate rest;


	@PostConstruct
	private void setup() {
		rest = rest.withBasicAuth("testuser", "pass");
	}


	@Test
	void contextLoads() {
	}



	@Test
	public void createsAndRetrievesUser() {
		UserCreateCommand request = new UserCreateCommand("Donald", "Knuth", "US");

		UserCreatedResponse created = rest.postForObject("/v1/user", request, UserCreatedResponse.class);

		assertNotNull(created);
		assertNotNull(created.timestamp());

		UserResponse retrieved = rest.getForObject("/v1/user/{id}", UserResponse.class, created.id());

		assertNotNull(retrieved);
		assertEquals(created.id(), retrieved.user().getId());
		assertEquals(request.firstName(), retrieved.user().getFirstName());
		assertEquals(request.lastName(), retrieved.user().getLastName());
		assertEquals(request.location(), retrieved.user().getLocation());

		UserListResponse allUsers = rest.getForObject("/v1/user", UserListResponse.class);

		assertNotNull(allUsers);
		assertNotNull(allUsers.users());
		assertEquals(1, allUsers.users().size());
		assertTrue(allUsers.users().contains(retrieved.user()));

		UserListResponse usersByLocation = rest.getForObject("/v1/user?location={location}", UserListResponse.class, request.location());

		assertNotNull(usersByLocation);
		assertNotNull(usersByLocation.users());
		assertEquals(1, usersByLocation.users().size());
		assertTrue(usersByLocation.users().contains(retrieved.user()));

		rest.delete("/v1/user/{id}", created.id());
	}


	@Test
	public void updatesAndRetrievesUpdatedUser() {
		UserCreateCommand request = new UserCreateCommand("Donald", "Knuth", "US");

		UserCreatedResponse created = rest.postForObject("/v1/user", request, UserCreatedResponse.class);

		assertNotNull(created);
		assertNotNull(created.timestamp());

		UserResponse retrieved = rest.getForObject("/v1/user/{id}", UserResponse.class, created.id());

		assertNotNull(retrieved);
		assertEquals(created.id(), retrieved.user().getId());
		assertEquals(request.firstName(), retrieved.user().getFirstName());
		assertEquals(request.lastName(), retrieved.user().getLastName());
		assertEquals(request.location(), retrieved.user().getLocation());

		String newLocation = "UK";
		UserUpdateRequest updateRequest = new UserUpdateRequest(newLocation);
		rest.put("/v1/user/{id}", updateRequest, created.id());

		UserResponse updated = rest.getForObject("/v1/user/{id}", UserResponse.class, created.id());

		assertNotNull(updated);
		assertEquals(created.id(), updated.user().getId());
		assertEquals(request.firstName(), updated.user().getFirstName());
		assertEquals(request.lastName(), updated.user().getLastName());
		assertEquals(newLocation, updated.user().getLocation());

		UserResponse retrievedUpdated = rest.getForObject("/v1/user/{id}", UserResponse.class, created.id());

		assertNotNull(retrievedUpdated);
		assertEquals(created.id(), retrievedUpdated.user().getId());
		assertEquals(request.firstName(), retrievedUpdated.user().getFirstName());
		assertEquals(request.lastName(), retrievedUpdated.user().getLastName());
		assertEquals(newLocation, retrievedUpdated.user().getLocation());

		rest.delete("/v1/user/{id}", created.id());
	}


	@Test
	public void deletesUserAndObservesDeletion() {
		UserCreateCommand request = new UserCreateCommand("Donald", "Knuth", "US");

		UserCreatedResponse created = rest.postForObject("/v1/user", request, UserCreatedResponse.class);

		assertNotNull(created);
		assertNotNull(created.timestamp());

		UserResponse retrieved = rest.getForObject("/v1/user/{id}", UserResponse.class, created.id());

		assertNotNull(retrieved);
		assertEquals(created.id(), retrieved.user().getId());

		rest.delete("/v1/user/{id}", created.id());

		ResponseEntity<?> response = rest.getForEntity("/v1/user/{id}", UserResponse.class, created.id());

		assertNotNull(response);
		assertEquals(404, response.getStatusCode().value());
	}

}
