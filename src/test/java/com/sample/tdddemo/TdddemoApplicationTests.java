package com.sample.tdddemo;

import com.sample.tdddemo.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//This uses complete Spring context so do not run this often. Use Unit test for smaller components
//@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class TdddemoApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void saveNewCustomer_ReturnsGoodResponse() throws Exception{
		ResponseEntity<Customer> postResponse = testRestTemplate.postForEntity("/api/user", new Customer("Donald Duck",111), Customer.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void saveAndgetCustomerDetails_returnsSameResult() throws Exception{
		ResponseEntity<Customer> postResponse = testRestTemplate.postForEntity("/api/user", new Customer("Donald Duck",111), Customer.class);
		ResponseEntity<Customer> response = testRestTemplate.getForEntity("/api/users/" + postResponse.getBody().getId(), Customer.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getName()).isEqualTo("Donald Duck");
		assertThat(response.getBody().getAge()).isEqualTo(111);
	}


}
