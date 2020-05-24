package com.stud.st.controller;

import java.util.List;
import com.stud.st.model.Pizza;
import com.stud.st.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizzeria")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping(value = "/pizza", consumes = "application/json")
    public List<Pizza> getPizzas() {
        return pizzaService.getPizzas();
    }

    @GetMapping(value = "/pizza/{id}", consumes = "application/json")
    public Pizza getPizza(@PathVariable ("id") int id) {
        return pizzaService.getPizza(id);
    }

    @PostMapping(value = "/pizza", consumes = "application/json")
    public Pizza addPizza(@RequestBody Pizza pizza) {
        return pizzaService.addPizza(pizza);
    }

    @PatchMapping(value = "/pizza/{id}", consumes = "application/json")
    public Pizza updatePizza(@PathVariable ("id") int id, @RequestBody Pizza pizza) {
        return pizzaService.updatePizza(id, pizza);
    }

    @DeleteMapping("/pizza/{id}")
    public void deletePizza(@PathVariable ("id") int id) {
        pizzaService.deletePizza(id);
    }
}
