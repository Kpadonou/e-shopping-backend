package com.rem.eshop.controllers;

import java.util.List;
import java.util.Optional;

import com.rem.eshop.models.ShoppingCartItem;
import com.rem.eshop.repositories.ShoppingCartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShoppingCartItemController {
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @GetMapping("/shopping-cart-items")
    public List<ShoppingCartItem> getAllShoppingCartItems() {
        return shoppingCartItemRepository.findAll();
    }

    @GetMapping("/cart/{id}/shopping-cart-items")
    public List<ShoppingCartItem> getShoppingCartItemsByCart(@PathVariable Long id) {
        return shoppingCartItemRepository.findByShoppingCartId(id);
    }

    @GetMapping("/shopping-cart-items/{id}")
    public ShoppingCartItem getShoppingCartItemByID(@PathVariable Long id) {
        Optional<ShoppingCartItem> optShoppingCartItem = shoppingCartItemRepository.findById(id);
        if (optShoppingCartItem.isPresent()) {
            return optShoppingCartItem.get();
        } else {
            throw new RuntimeException("ShoppingCartItem not found with id " + id);
        }
    }

    @PostMapping("/shopping-cart-items")
    public ShoppingCartItem createShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        return shoppingCartItemRepository.save(shoppingCartItem);
    }

    @PutMapping("/shopping-cart-items/{id}")
    public ShoppingCartItem updateShoppingCartItem(@PathVariable Long id,
            @RequestBody ShoppingCartItem shoppingCartItemUpdated) {
        return shoppingCartItemRepository.findById(id).map(shoppingCartItem -> {
            shoppingCartItem.setQuantity(shoppingCartItemUpdated.getQuantity());
            shoppingCartItem.setProduct(shoppingCartItemUpdated.getProduct());
            shoppingCartItem.setShoppingCart(shoppingCartItemUpdated.getShoppingCart());
            return shoppingCartItemRepository.save(shoppingCartItem);
        }).orElseThrow(() -> new RuntimeException("ShoppingCartItem not found with id " + id));
    }

    @DeleteMapping("/shopping-cart-items/{id}")
    public StringResponseFormat deleteShoppingCartItem(@PathVariable Long id) {
        return shoppingCartItemRepository.findById(id).map(shoppingCartItem -> {
            shoppingCartItemRepository.delete(shoppingCartItem);
            return new StringResponseFormat("Delete Successfully!");
        }).orElseThrow(() -> new RuntimeException("ShoppingCartItem not found with id " + id));
    }
}
