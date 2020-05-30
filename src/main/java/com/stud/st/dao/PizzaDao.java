package com.stud.st.dao;

import java.util.List;

import com.stud.st.model.Pizza;

public interface PizzaDao {

    List<Pizza> getPizzas();

    Pizza addPizza(Pizza pizza);

    Pizza getPizza(int id);

    Pizza updatePizza(int id, Pizza pizza);

    void deletePizza(int id);

    Pizza addPizzaWithPropagation(Pizza pizza) throws Exception;
}
