package com.stud.st.service;

import com.stud.st.dao.PizzaDao;
import com.stud.st.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PizzaServiceImpl implements PizzaService {

    @Autowired
    PizzaDao pizzaDao;


    @Override
    public List<Pizza> getPizzas() {
        return pizzaDao.getPizzas();
    }

    @Override
    public Pizza addPizza(Pizza pizza) {
        return pizzaDao.addPizza(pizza);
    }

    @Override
    public Pizza getPizza(int id) {
        return pizzaDao.getPizza(id);
    }

    @Override
    public Pizza updatePizza(int id, Pizza pizza) {
        return pizzaDao.updatePizza(id, pizza);
    }

    @Override
    public void deletePizza(int id) {
        pizzaDao.deletePizza(id);
    }
}
