package com.wxs.springboot.mongo.controller;

import com.wxs.springboot.mongo.domain.Customer;
import com.wxs.springboot.mongo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 17:00
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping(value = "/add")
    public Object add(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping(value = "/query")
    public Object selectGet(@RequestParam String name) {
        return customerRepository.findByFirstName(name);
    }

}
