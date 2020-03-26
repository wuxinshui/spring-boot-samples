package com.wxs.springboot.mongo.domain;

import org.springframework.data.annotation.Id;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/12/26 17:59
 */
public class Customer {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
