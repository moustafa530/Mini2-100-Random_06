package com.example.miniapp.services;

import com.example.miniapp.models.Customer;
import com.example.miniapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Add a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by ID
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.orElse(null);
    }

    // Update customer information
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            return customerRepository.save(existingCustomer);
        } else {
            return null;
        }
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Find customers by email domain
    public List<Customer> findCustomersByEmailDomain(String domain) {
        return customerRepository.findCustomersByEmailDomain(domain);
    }

    // Find customers by phone prefix
    public List<Customer> findCustomersByPhonePrefix(String prefix) {
        return customerRepository.findCustomersByPhonePrefix(prefix);
    }
}
