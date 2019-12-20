package com.sample.tdddemo;

import com.sample.tdddemo.error.CustomerNotFoundException;
import com.sample.tdddemo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //Need the setter method as we are testing without Spring boot and plain Mockito
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerDetails(long cusID) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(cusID);
        if(customer==null)
            throw new CustomerNotFoundException(cusID);
        return customer;
    }

    public Customer saveCustomerDetails(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

}
