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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PizzaDaoImpl implements PizzaDao {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Pizza> getPizzas() {
        log.info("getPizzas(): BEGIN");
        Criteria criteria = getSession().createCriteria(Pizza.class);
        criteria.add(Restrictions.lt("price", 500));
        List<Pizza> result = (List<Pizza>)criteria.list();
        log.info("getPizzas(): result={}", result);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("getPizzas(): END");
        result = (List<Pizza>) criteria.list();
        log.info("getPizzas(): again result={}", result);
        return result;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Pizza addPizza(Pizza pizza) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("addPizza(): BEGIN");
        getSession().persist(pizza);
        log.info("addPizza(): END pizza={}", pizza);
        return pizza;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Pizza getPizza(int id) {
        log.info("getPizza(): BEGIN");
        Criteria criteria = getSession().createCriteria(Pizza.class);
        criteria.add(Restrictions.lt("price", 500));
        Pizza pizza = ((List<Pizza>)criteria.list()).get(0);
        log.info("getPizza(): END");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((List<Pizza>)criteria.list()).get(0);

        return pizza;
    }

    @Override
    @Transactional
    public Pizza updatePizza(int id, Pizza pizza) {
        log.info("updatePizza(): BEGIN");
        getSession().update(pizza);
        log.info("updatePizza(): END");
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
