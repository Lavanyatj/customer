package com.example.customer.service;

import com.example.customer.model.CustomerPojo;

import java.util.List;

public interface CustomerService {

    CustomerPojo addCustomer(CustomerPojo customer);

    List<CustomerPojo> getAllCustomers();

    CustomerPojo getCustomerById(Long id);

    void deleteCustomerById(Long id);

    String updateCustomer(CustomerPojo customer);


}
