package com.stud.st.dao;

import com.stud.st.model.Pizza;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PizzaDaoImpl implements PizzaDao {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Pizza> getPizzas() {
        log.info("getPizzas(): BEGIN");
        Criteria criteria = getSession().createCriteria(Pizza.class);
        List<Pizza> result = (List<Pizza>)criteria.list();
        log.info("getPizzas(): END");
        return result;
    }

    @Override
    @Transactional
    public Pizza addPizza(Pizza pizza) {
        log.info("getPizzas(): BEGIN");
        getSession().persist(pizza);
        log.info("getPizzas(): END");
        return pizza;
    }

    @Override
    @Transactional
    public Pizza getPizza(int id) {
        log.info("getPizzas(): BEGIN");
        Criteria criteria = getSession().createCriteria(Pizza.class);
        criteria.add(Restrictions.eq("id", id));
        Pizza pizza = ((List<Pizza>)criteria.list()).get(0);
        log.info("getPizzas(): END");
        return pizza;
    }

    @Override
    @Transactional
    public Pizza updatePizza(int id, Pizza pizza) {
        return pizza;
    }

    @Override
    @Transactional
    public void deletePizza(int id) {
        Query query = getSession().createSQLQuery("delete from pizza where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
