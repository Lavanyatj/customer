package com.example.customer.controller;

import java.util.List;

import com.example.customer.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.model.CustomerPojo;
import com.example.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Operation(summary = "Find all Customers", description="Return list of customer object")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="success"),
			@ApiResponse(responseCode="404",description="notFound"),
			@ApiResponse(responseCode="500",description="Internal Server Error")
	})
	@RequestMapping(value="/findAll", produces="application/json", method=RequestMethod.GET)
	public ResponseEntity<List<CustomerPojo>> findAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}


	@Operation(
			summary = "Find Customer by ID",
			description = "Provide an ID to look up specific customer"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@RequestMapping(value="/findById/{id}", produces="application/json", method=RequestMethod.GET)
	ResponseEntity<CustomerPojo> findCustomerById(@PathVariable("id") Long id) {
		CustomerPojo customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(customer);
	}

	@Operation(
			summary = "Add a new Customer",
			description = "Provide customer details to add a new customer"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "409", description = "Conflict"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@RequestMapping(value="/addCustomer", consumes="application/json", produces="application/json", method=RequestMethod.POST)
	ResponseEntity<CustomerPojo> addCustomer(@RequestBody CustomerPojo customer) {
		CustomerPojo createdCustomer = customerService.addCustomer(customer);
		return ResponseEntity.status(201).body(createdCustomer);
	}

	@Operation(
			summary = "update existing Customer",
			description = "updates customer details based on the provided information"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@RequestMapping(value="/updateCustomer", consumes="application/json", produces="application/json", method=RequestMethod.PUT)
	ResponseEntity<String> updateCustomer(@RequestBody CustomerPojo customer) {
		String response = customerService.updateCustomer(customer);
		return ResponseEntity.ok(response);
	}

	@Operation(
			summary = "Delete Customer by ID",
			description = "Provide an ID to delete a specific customer"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@RequestMapping(value="/deleteById/{id}", produces="application/json", method=RequestMethod.DELETE)
	ResponseEntity<String> deleteCustomerById(@PathVariable("id") Long id) {
		customerService.deleteCustomerById(id);
		return ResponseEntity.ok("specified customer deleted");
	}






	
	
	
}
