package com.stud.st.dao;

import java.util.List;
import java.util.Map;

import com.stud.st.model.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDao {
    List<Order> getOrders();

    Order getOrder(int id);

    Order placeOrder(Order order);

    Order orderPropagation(Map<String, String> obj);
}
