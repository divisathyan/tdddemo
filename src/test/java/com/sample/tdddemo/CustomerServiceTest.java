package com.sample.tdddemo;

import com.sample.tdddemo.error.CustomerNotFoundException;
import com.sample.tdddemo.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/*
Do not involve Spring as we need basic test cases to connect to Repository
Use Mock and not MockBean
 */
@ExtendWith({MockitoExtension.class, SpringExtension.class})
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    //You cannot Autowire without SpringBootTest. We are using plain Mockito
    private CustomerService customerService;

    @BeforeEach
    private void setUp() {
        customerService = new CustomerService();
        customerService.setCustomerRepository(customerRepository);
    }

    @Test
    void getCustomerDetails_willReturnDetails() throws Exception {
        when(customerRepository.findById(anyLong())).thenReturn(new Customer("John Smith", 21));

        Customer customer = customerService.getCustomerDetails(1);
        assertThat(customer.getName()).isEqualTo("John Smith");
        assertThat(customer.getAge()).isEqualTo(21);
    }

    @Test
    void getCustomerDetailsWrongID_throwsException() throws Exception {
        given(customerRepository.findById(anyLong())).willReturn(null);

        assertThrows(CustomerNotFoundException.class,()->{
            customerService.getCustomerDetails(1);
        });

    }

    @Test
    void saveCustomerDetails_willReturnDetails() throws Exception {
        when(customerRepository.save(any())).thenReturn(new Customer("John Smith", 21));

        Customer customer = customerService.saveCustomerDetails(new Customer("John Smith", 21));
        assertThat(customer.getName()).isEqualTo("John Smith");
        assertThat(customer.getAge()).isEqualTo(21);
    }
}