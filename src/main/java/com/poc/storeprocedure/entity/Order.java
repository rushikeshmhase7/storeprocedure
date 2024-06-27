package com.poc.storeprocedure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

//import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_id")
    private int oId;
    private Date orderDate;
    private double totalAmount;
    @ManyToOne
    @JoinColumn(name = "customer_Id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer; // ManyToOne relationship with Customer

    public Order(){}

    public int getOrderId() {
        return oId;
    }

    public void setOrderId(int orderId) {
        this.oId = orderId;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + oId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", customer=" + customer +
                '}';
    }
}
