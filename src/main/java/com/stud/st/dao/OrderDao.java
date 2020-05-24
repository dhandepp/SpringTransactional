package com.stud.st.dao;

import java.util.List;
import com.stud.st.model.Order;

public interface OrderDao {
    List<Order> getOrders();
    Order getOrder(int id);
    Order placeOrder(Order order);
}
