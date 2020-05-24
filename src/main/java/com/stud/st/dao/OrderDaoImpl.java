package com.stud.st.dao;

import com.stud.st.model.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Order> getOrders() {
        Criteria criteria = getSession().createCriteria(Order.class);
        return (List<Order>) criteria.list();
    }

    @Override
    @Transactional
    public Order getOrder(int id) {
        Criteria criteria = getSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("id", id));
        return ((List<Order>) criteria.list()).get(0);
    }

    @Override
    @Transactional
    public Order placeOrder(Order order) {
        getSession().persist(order);
        return order;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
