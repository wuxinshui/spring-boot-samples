package com.wxs.springboot.mongo.repository;

import com.wxs.springboot.mongo.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/12/26 18:00
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

}
