package com.stud.st.service;

import com.stud.st.dao.OrderDao;
import com.stud.st.dao.PizzaDao;
import com.stud.st.model.Order;
import com.stud.st.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    PizzaDao pizzaDao;

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public Order getOrder(int id) {
        return orderDao.getOrder(id);
    }

    @Override
    public Order placeOrder(List<Map<String, Integer>> orderDetails) {
        Order order = new Order();
        StringBuilder sb = new StringBuilder();
        for(Map<String, Integer> pizzaOrder : orderDetails) {
            Pizza pizza = pizzaDao.getPizza(pizzaOrder.get("id"));
            sb.append(pizzaOrder.get("quantity")).append(" x ").append(pizza.getName()).append(", ");
            order.charge(pizzaOrder.get("quantity") * pizza.getPrice());
        }
        order.setOrderDetails(sb.substring(0, sb.length() - 2));

        System.out.println("Final Order= "+ order);
        return orderDao.placeOrder(order);
    }
}
