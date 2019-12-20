package com.sample.tdddemo;

import com.sample.tdddemo.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    /*
    Do not use actual DB operations in the test but use the entity manager
     */
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void getCustomerDetails_returnsGoodData() {
        //customerRepository.save(new Customer("John Smith",21));
        entityManager.persist(new Customer("John Smith",21));

        Customer customer = customerRepository.findById(1);
        assertThat(customer.getName()).isEqualTo("John Smith");
        assertThat(customer.getAge()).isEqualTo(21);

    }

}
