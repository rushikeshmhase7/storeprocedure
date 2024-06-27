package com.poc.storeprocedure.repository;

import com.poc.storeprocedure.entity.BNYUser;
import com.poc.storeprocedure.entity.Customer;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//import javax.persistence.NamedStoredProcedureQueries;
//import javax.persistence.NamedStoredProcedureQuery;

//import javax.persistence.NamedStoredProcedureQuery;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
        name = "GetCustomer",
        procedureName = "GetCustomerDetails",
        resultClasses = Customer.class),
        @NamedStoredProcedureQuery(
        name = "getCustomersWithOrders",
        procedureName = "GetCustomersWithOrdersRightJoin",
        resultClasses = Customer.class)
})
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

//    @Query(value = "CALL GetCustomerDetails(:customer_id)", nativeQuery = true)
//    Customer getCustomerDetails(@Param("customer_id") int id);


    @Procedure(name = "GetCustomer")
    Customer getCustomerDetails(@Param("customer_id") int id);

    @Query(value = "CALL GetCustomerDetailsWithOrders(:customer_id)", nativeQuery = true)
    Customer getCustomerDetailsWithOrders(@Param("customer_id") int id);


        @Query(value = "CALL getUserBYId(:userId)", nativeQuery = true)
        Object[] getUserDetails(@Param("userId") int id);


    // Right Join
//    @Query(value = "CALL GetCustomersWithOrdersRightJoin()", nativeQuery = true)
        @Procedure(name = "getCustomersWithOrders")
        List<Object[]> getCustomersWithOrdersRightJoin();

    //Left Join
    @Query(value = "CALL GetCustomersWithOrdersLeftJoin()", nativeQuery = true)
    List<Object[]> getCustomersWithOrdersLeftJoin();

    // Inner Join
    @Query(value = "CALL GetCustomersWithOrdersInnerJoin()", nativeQuery = true)
    List<Object[]> getCustomersWithOrdersInnerJoin();

    // Full Join
    @Query(value = "CALL GetCustomersWithOrdersFullJoin()", nativeQuery = true)
    List<Object[]> getCustomersWithOrdersFullJoin();

    // Cross Join
    @Query(value = "CALL GetCustomersWithOrdersCrossJoin()", nativeQuery = true)
    List<Object[]> getCustomersWithOrdersCrossJoin();

    // Self Join
    @Query(value = "CALL GetManagersAndEmployees()", nativeQuery = true)
    List<Object[]> getManagersAndEmployees();
}
