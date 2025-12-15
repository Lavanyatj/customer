package com.example.customer.service;

import com.example.customer.exception.CustomerAlreadyExistsException;
import com.example.customer.exception.NoSuchCustomerExistsException;
import com.example.customer.model.CustomerPojo;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Add a new customer
    @Override
    public CustomerPojo addCustomer(CustomerPojo customer) {

        // Check if email already exists
        customerRepository.findByEmail(customer.getEmail())
                .ifPresent(existing -> {
                    throw new CustomerAlreadyExistsException(
                            "Customer with email " + customer.getEmail() + " already exists."
                    );
                });

        return customerRepository.save(customer);
    }

    // Get all customers
    @Override
    public List<CustomerPojo> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    @Override
    public CustomerPojo getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchCustomerExistsException(
                                "Customer with id " + id + " does not exist."
                        )
                );
    }

    // Delete customer by ID
    @Override
    public void deleteCustomerById(Long id) {
        CustomerPojo customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchCustomerExistsException(
                                "Customer with id " + id + " does not exist."
                        )
                );

        customerRepository.delete(customer);
    }

    // Update existing customer
    @Override
    public String updateCustomer(CustomerPojo customer) {

        CustomerPojo existing = customerRepository.findById(customer.getId())
                .orElseThrow(() ->
                        new NoSuchCustomerExistsException(
                                "Customer with id " + customer.getId() + " does not exist."
                        )
                );

        // Update only non-null fields
        Optional.ofNullable(customer.getName()).ifPresent(existing::setName);
        Optional.ofNullable(customer.getEmail()).ifPresent(existing::setEmail);
        Optional.ofNullable(customer.getAddress()).ifPresent(existing::setAddress);

        customerRepository.save(existing);

        return "Customer updated successfully";
    }
}
