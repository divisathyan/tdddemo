package com.sample.tdddemo.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Valid
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull(message = "Full name must be provided")
    private String name;

    @Min(value = 1, message = "Minimum age is 1")
    @Max(value = 150, message = "Maximum allowed age is 150")
    private int age;

    public Customer(String fullName, int age){
        this.name = fullName;
        this.age = age;
    }

    public Customer(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() { return this.age; }

    public void setAge(int age) {
        this.age = age;
    }

}
