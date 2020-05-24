package com.stud.st.service;

import com.stud.st.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> getOrders();
    Order getOrder(int id);
    Order placeOrder(List<Map<String, Integer>> orderDetails);
}
