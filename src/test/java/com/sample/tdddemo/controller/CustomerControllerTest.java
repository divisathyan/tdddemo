package com.sample.tdddemo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.tdddemo.CustomerService;
import com.sample.tdddemo.error.CustomerNotFoundException;
import com.sample.tdddemo.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
This looks similar to the Integration test we wrote but it tests only the Controller using WebMvcTest.
It does not create the entire Spring app context but only the required beans to run this Controller
 */

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomerWithID_returnsCustomerDetails() throws Exception{
        //Inject the mocked bean as it is a Slice test, make it a MockBean
        given(customerService.getCustomerDetails(anyLong())).willReturn(new Customer("John Smith",21));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("John Smith"))
                .andExpect(jsonPath("age").value(21));
    }

    @Test
    public void getCustomerWithInvalidID_returns404() throws Exception {
        given(customerService.getCustomerDetails(anyLong())).willThrow(new CustomerNotFoundException(5L));
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(containsString("User not found")));

        } catch (CustomerNotFoundException cnfe){
            assertThat(cnfe).hasMessageContaining("User not found");
        }

    }

    @Test
    public void addCustomer_ReturnsOK() throws Exception {
        given(customerService.saveCustomerDetails(any())).willReturn(new Customer("Harry Potter",11));
        Customer user = new Customer("Harry Potter",11);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Harry Potter"));
    }

    @Test
    public void addCustomerWithBadData_WillThrowException() throws Exception {
        given(customerService.saveCustomerDetails(any())).willThrow(ConstraintViolationException.class);
        Customer user = new Customer("Harry Potter",151);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    public void addCustomerWithGenericError_WillThrowException() throws Exception {
        given(customerService.saveCustomerDetails(any())).willThrow(RuntimeException.class);
        Customer user = new Customer();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

}
