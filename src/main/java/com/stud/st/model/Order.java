package com.stud.st.model;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "details")
    private String orderDetails;

    @Column(name = "total")
    private float total;

    public Order() {
        super();
    }

    public Order(String orderDetails, float total) {
        this();
        this.orderDetails = orderDetails;
        this.total = total;
    }

    public Order(int id, String orderDetails, float total) {
        this();
        this.id = id;
        this.orderDetails = orderDetails;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void charge(float charge) {
        this.total = this.total + charge;
    }

    @Override
    public String toString() {
        return "Order[" +
                "id=" + id +
                ", orderDetails='" + orderDetails + '\'' +
                ", total=" + total +
                ']';
    }
}
