package com.rem.eshop.repositories;

import java.util.List;

import com.rem.eshop.models.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
