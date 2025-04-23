package com.example.miniapp.controllers;

import com.example.miniapp.models.Customer;
import com.example.miniapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Add Customer
    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // Get All Customers
    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get Customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // Update Customer
    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    // Delete Customer
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer with ID " + id + " deleted successfully.";
    }

    // Find Customers by Email Domain
    @GetMapping("/findByEmailDomain")
    public List<Customer> findCustomersByEmailDomain(@RequestParam String domain) {
        return customerService.findCustomersByEmailDomain(domain);
    }

    // Find Customers by Phone Prefix
    @GetMapping("/findByPhonePrefix")
    public List<Customer> findCustomersByPhonePrefix(@RequestParam String prefix) {
        return customerService.findCustomersByPhonePrefix(prefix);
    }
}
