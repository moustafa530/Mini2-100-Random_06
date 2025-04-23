package com.example.miniapp.repositories;

import com.example.miniapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 1. Find customers by email domain (e.g., '@gmail.com')
    @Query("SELECT c FROM Customer c WHERE c.email LIKE %?1")
    List<Customer> findCustomersByEmailDomain(String domain);

    // 2. Find customers by phone prefix (e.g., '+20')
    @Query("SELECT c FROM Customer c WHERE c.phoneNumber LIKE ?1%")
    List<Customer> findCustomersByPhonePrefix(String prefix);

}
