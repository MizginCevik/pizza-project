package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Pizza;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/design")
public class DesignPizzaController {

    private final PizzaRepository pizzaRepository; //without final still be working

    public DesignPizzaController(PizzaRepository pizzaRepository) { //we need initialization so constructor needed
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping      //localhost:8080/design
    public String showDesignForm(Model model) {

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);

        return "/design";

    }

    @PostMapping("/createPizza") //we can remove this endpoint, or we can go to html and change the action
    public String processPizza(@ModelAttribute("pizza") Pizza pizza) {

        pizza.setId(UUID.randomUUID());
        pizzaRepository.createPizza(pizza);

        return "redirect:/orders/current?pizzaId=" + pizza.getId(); // ? ->query parameter

    }

}
//command+shift+F -> shows all used UUID
