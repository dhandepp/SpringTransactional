package com.stud.st.controller;

import com.stud.st.dao.OrderDao;
import com.stud.st.model.Order;
import com.stud.st.model.Pizza;
import com.stud.st.service.OrderService;
import com.stud.st.service.PizzaService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@RestController
@RequestMapping("/pizzeria")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDao orderDao;

    @Autowired
    PizzaService pizzaService;

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

    @GetMapping(value = "/isolation")
    public List<Pizza> foo() throws InterruptedException {

        FutureTask getPizzasTask = new FutureTask(() -> pizzaService.getPizzas());

        Thread getPizzaThread = new Thread(getPizzasTask, "getPizzas");

        Thread addPizzaThread = new Thread(() -> {
            Pizza pizza = new Pizza();
            pizza.setName(RandomStringUtils.randomAlphabetic(6));
            pizza.setPrice(130);
            pizzaService.addPizza(pizza);
        }, "addPizza");

        getPizzaThread.start();
        addPizzaThread.start();

        List<Pizza> pizzas = null;

        for (int i = 0; i < 10; i++) {
            if (getPizzasTask.isDone()) {
                try {
                    pizzas = (List<Pizza>) getPizzasTask.get();
                    log.info("GetPizzas ={}", pizzas);
                    break;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
        }
        if (pizzas == null) {
            log.warn("Unable to get pizzas from the transaction");
        }
        return pizzas;
    }

    @PostMapping(value = "/propagation")
    public Order bar(@RequestBody Map<String, String> pizzaWithOrder) {
        return orderDao.orderPropagation(pizzaWithOrder);
    }
}
