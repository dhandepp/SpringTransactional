package com.stud.st.controller;

import com.stud.st.model.Order;
import com.stud.st.model.Pizza;
import com.stud.st.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pizzeria")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/orders", consumes = "application/json")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping(value = "/orders/{id}", consumes = "application/json")
    public Order getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }

    @PostMapping(value = "/orders", consumes = "application/json")
    public Order placeOrder(@RequestBody List<Map<String, Integer>> orderDetails) {
        return orderService.placeOrder(orderDetails);
    }
}
