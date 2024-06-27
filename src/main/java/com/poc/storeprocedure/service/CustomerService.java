package com.poc.storeprocedure.service;

import com.poc.storeprocedure.dto.CustomerDto;
import com.poc.storeprocedure.dto.CustomerOrderDTO;
import com.poc.storeprocedure.entity.BNYUser;
import com.poc.storeprocedure.entity.Customer;
import com.poc.storeprocedure.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

//    @Autowired
//    private ModelMapper  mapper;

    @Transactional
    public CustomerDto getCustomer(int id) {
       Customer customer=  customerRepository.getCustomerDetails(id);
       CustomerDto customerDto=new CustomerDto();
       customerDto.setId(customer.getId());
       customerDto.setName(customer.getName());
        return customerDto;
    }

    @Transactional
    public Customer getCustomerDetailsWithOrders(int id) {
        return customerRepository.getCustomerDetailsWithOrders(id);
    }

    @Transactional
    public Object[] getAllUsers(int id) {
        return customerRepository.getUserDetails(id);
    }


    @PersistenceContext
    private EntityManager entityManager;



    public List<CustomerOrderDTO> getCustomersWithOrdersRightJoinDTO() {
        List<CustomerOrderDTO> result = entityManager.createNativeQuery("CALL GetCustomersWithOrdersRightJoin()")
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        CustomerOrderDTO dto = new CustomerOrderDTO();
                        dto.setCustomerId((Integer) tuple[0]);
                        dto.setCustomerName((String) tuple[1]);
                        dto.setOrderId((Integer) tuple[2]);
                        dto.setOrderDate((Date) tuple[3]);
                        dto.setTotalAmount((Double) tuple[4]);
                        return dto;
                    }

                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                })
                .getResultList();

        return result;
    }
}
