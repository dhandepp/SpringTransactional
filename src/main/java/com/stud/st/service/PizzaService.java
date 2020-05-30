package com.stud.st.service;

import com.stud.st.model.Pizza;

import java.util.List;

public interface PizzaService {

    List<Pizza> getPizzas();

    Pizza addPizza(Pizza pizza);

    Pizza getPizza(int id);

    Pizza updatePizza(int id, Pizza pizza);

    void deletePizza(int id);
}
