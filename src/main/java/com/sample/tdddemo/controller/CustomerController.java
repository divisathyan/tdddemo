package com.sample.tdddemo.controller;

import com.sample.tdddemo.CustomerService;
import com.sample.tdddemo.error.CustomerNotFoundException;
import com.sample.tdddemo.model.Customer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Get user details using ID")
    @GetMapping("/users/{id}")
    public Customer getCustomerDetails(@PathVariable(name = "id") long cusID) throws Exception {
        return customerService.getCustomerDetails(cusID);
    }

    @ApiOperation(value = "Save user data")
    @PostMapping("/user")
    public Customer saveCustomerDetails(@Valid @RequestBody Customer newCustomer) throws Exception {
        return customerService.saveCustomerDetails(newCustomer);
    }

    /*
    It is better to have a centralized Exception handler than copying the logic to every controller
    //Any business exception is converted to HTTP 404 status. We can also declare the status on Exception class
     */
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public void handleCustomerException(CustomerNotFoundException ex) {

//    }

}
