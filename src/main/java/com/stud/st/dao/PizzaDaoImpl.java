package com.stud.st.dao;

import com.stud.st.model.Pizza;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PizzaDaoImpl implements PizzaDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Pizza> getPizzas() {
        Criteria criteria = getSession().createCriteria(Pizza.class);
        return (List<Pizza>) criteria.list();
    }

    @Override
    @Transactional
    public Pizza addPizza(Pizza pizza) {
        getSession().persist(pizza);
        return pizza;
    }

    @Override
    @Transactional
    public Pizza getPizza(int id) {
        Criteria criteria = getSession().createCriteria(Pizza.class);
        criteria.add(Restrictions.eq("id", id));
        return ((List<Pizza>) criteria.list()).get(0);
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
