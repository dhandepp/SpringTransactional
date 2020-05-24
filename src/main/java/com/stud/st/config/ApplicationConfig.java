package com.stud.st.config;

import com.stud.st.dao.OrderDao;
import com.stud.st.dao.OrderDaoImpl;
import com.stud.st.dao.PizzaDao;
import com.stud.st.dao.PizzaDaoImpl;
import com.stud.st.service.OrderService;
import com.stud.st.service.OrderServiceImpl;
import com.stud.st.service.PizzaService;
import com.stud.st.service.PizzaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    @Bean
    PizzaService getPizzaService() {
        return new PizzaServiceImpl();
    }

    @Bean
    OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }

    @Bean
    PizzaDao getPizzaDao() {
        return new PizzaDaoImpl();
    }
}
