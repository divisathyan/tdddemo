package com.sample.tdddemo;

import com.sample.tdddemo.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    //We do not have to write interface methods explicitly, just for readability
    Customer findById(long cusID);
}
