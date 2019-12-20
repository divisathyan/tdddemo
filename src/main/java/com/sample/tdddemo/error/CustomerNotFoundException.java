package com.sample.tdddemo.error;

//Checked exception did not work for TDD
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id){
        super("User not found with ID: " + id);
    }

}
