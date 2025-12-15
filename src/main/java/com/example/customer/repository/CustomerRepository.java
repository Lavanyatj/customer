package com.example.customer.repository;

import com.example.customer.model.CustomerPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerPojo, Long> {
    Optional<CustomerPojo> findByEmail(String email);
}
