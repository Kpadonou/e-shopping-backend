package com.rem.eshop.controllers;

import java.util.List;
import java.util.Optional;

import com.rem.eshop.models.ShoppingCart;
import com.rem.eshop.repositories.ShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @GetMapping("/shopping-carts")
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    @GetMapping("/shopping-carts/{id}")
    public ShoppingCart getShoppingCartByID(@PathVariable Long id) {
        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findById(id);
        if (optShoppingCart.isPresent()) {
            return optShoppingCart.get();
        } else {
            throw new RuntimeException("ShoppingCart not found with id " + id);
        }
    }

    @PostMapping("/shopping-carts")
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    /*
     * @PutMapping("/shopping-carts/{id}") public ShoppingCart
     * updateShoppingCart(@PathVariable Long id, @RequestBody ShoppingCart
     * shoppingCartUpdated) { return
     * shoppingCartRepository.findById(id).map(shoppingCart -> {
     * shoppingCart.setDatePlaced(shoppingCartUpdated.getDatePlaced()); return
     * shoppingCartRepository.save(shoppingCart); }).orElseThrow(() -> new
     * NotFoundException("ShoppingCart not found with id " + id)); }
     */

    @DeleteMapping("/shopping-carts/{id}")
    public StringResponseFormat deleteShoppingCart(@PathVariable Long id) {
        return shoppingCartRepository.findById(id).map(shoppingCart -> {
            shoppingCartRepository.delete(shoppingCart);
            return new StringResponseFormat("Delete Successfully!");
        }).orElseThrow(() -> new RuntimeException("ShoppingCart not found with id " + id));
    }
}
