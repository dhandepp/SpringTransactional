package com.stud.st.dao;

import com.stud.st.model.Order;
import com.stud.st.model.Pizza;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    PizzaDao pizzaDao;

    @Override
    @Transactional
    public List<Order> getOrders() {
        log.info("getOrders(): BEGIN");
        List<Order> result = null;
        Criteria criteria = getSession().createCriteria(Order.class);
        result = (List<Order>) criteria.list();
        log.info("getOrders(): END");
        return result;
    }

    @Override
    @Transactional
    public Order getOrder(int id) {
        log.info("getOrder(): BEGIN");
        Criteria criteria = getSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("id", id));
        Order order = ((List<Order>) criteria.list()).get(0);
        log.info("getOrder(): END");
        return order;
    }

    @Override
    @Transactional
    public Order placeOrder(Order order) {
        log.info("placeOrder(): BEGIN");
        getSession().persist(order);
        log.info("placeOrder(): END");
        return order;
    }

    @Override
    @Transactional
    public Order orderPropagation(Map<String, String> obj) {
        log.info("orderPropagation(): BEGIN, req={}", obj);
        Pizza pizza = new Pizza(obj.get("name"), Integer.parseInt(obj.get("price")));
        try {
            pizzaDao.addPizzaWithPropagation(pizza);
        } catch (Exception ex) {
            log.warn("{}-{}", ex.getClass(), ex.getMessage());
        }
        log.info("orderPropagation(): pizza={}", pizza);
        if (pizza.getId() == null) {
            log.warn("Unable to add pizza, creating order with default details");
        }

        Order order = new Order("1 x " + pizza.getName(), pizza.getPrice());
        getSession().persist(order);
        log.info("orderPropagation(): END");
        return order;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
