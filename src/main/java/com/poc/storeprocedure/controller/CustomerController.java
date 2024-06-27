package com.poc.storeprocedure.controller;

import com.poc.storeprocedure.dto.CustomerDto;
import com.poc.storeprocedure.dto.CustomerOrderDTO;
import com.poc.storeprocedure.entity.BNYUser;
import com.poc.storeprocedure.entity.Customer;
import com.poc.storeprocedure.repository.CustomerRepository;
import com.poc.storeprocedure.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable int id) {
        CustomerDto customer = customerService.getCustomer(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customers-details/{id}")
    public ResponseEntity<Customer> getCustomerDetailsWithOrders(@PathVariable int id) {
        Customer customer = customerService.getCustomerDetailsWithOrders(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<Object[]> getUsers(@PathVariable int id) {
        Object[] user= customerService.getAllUsers(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customers/orders/right-join-dto")
    public ResponseEntity<List<CustomerOrderDTO>> getCustomersWithOrdersRightJoinDTO() {
        List<CustomerOrderDTO> result =customerService.getCustomersWithOrdersRightJoinDTO();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customers/orders/right-join")
    @Transactional
    public ResponseEntity<List<CustomerOrderDTO>> getCustomersWithOrdersRightJoin() {
        List<Object[]> result =customerRepository.getCustomersWithOrdersRightJoin();
        return ResponseEntity.ok(getObjectToDto(result));
    }

    @GetMapping("/customers/orders/left-join")
    public ResponseEntity<List<CustomerOrderDTO>> getCustomersWithOrdersLeftJoin() {
        List<Object[]> result =customerRepository.getCustomersWithOrdersLeftJoin();
        return ResponseEntity.ok(getObjectToDto(result));
    }

    // Inner Join
    @GetMapping("/customers/orders/inner-join")
    public ResponseEntity<List<CustomerOrderDTO>> getCustomersWithOrdersInnerJoin() {
        List<Object[]> result = customerRepository.getCustomersWithOrdersInnerJoin();
        return ResponseEntity.ok(getObjectToDto(result));
    }

    // Full Join
    @GetMapping("/customers/orders/full-join")
    public ResponseEntity<List<Object[]>> getCustomersWithOrdersFullJoin() {
        List<Object[]> result = customerRepository.getCustomersWithOrdersFullJoin();
        return ResponseEntity.ok(result);
    }

    // Cross Join
    @GetMapping("/customers/orders/cross-join")
    public ResponseEntity<List<CustomerOrderDTO>> getCustomersWithOrdersCrossJoin() {
        List<Object[]> result = customerRepository.getCustomersWithOrdersCrossJoin();
        return ResponseEntity.ok(getObjectToDto(result));
    }

    // Self Join
    @GetMapping("/employees/managers-employees")
    public ResponseEntity<List<Object[]>> getManagersAndEmployees() {
        List<Object[]> result = customerRepository.getManagersAndEmployees();
        return ResponseEntity.ok(result);
    }


    public List<CustomerOrderDTO> getObjectToDto(List<Object[]> obj){
        List<CustomerOrderDTO> customerOrderDTOList=new ArrayList<>();
        for(Object[] ob1:obj) {
            CustomerOrderDTO customerOrderDTO=new CustomerOrderDTO();
            if(ob1[0] != null) {
                customerOrderDTO.setCustomerId((int) ob1[0]);
            }
            if(ob1[1]!=null) {
                customerOrderDTO.setCustomerName((String) ob1[1]);
            }
            if(ob1[2]!=null) {
                customerOrderDTO.setOrderId((int) ob1[2]);
            }
            if(ob1[3]!=null) {
                customerOrderDTO.setOrderDate((Date) ob1[3]);
            }
            if(ob1[4]!=null) {
                customerOrderDTO.setTotalAmount((double) ob1[4]);
            }
            customerOrderDTOList.add(customerOrderDTO);
        }
        return customerOrderDTOList;
    }

}
