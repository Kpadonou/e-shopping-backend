package com.rem.eshop.repositories;

import java.util.List;

import com.rem.eshop.models.ShoppingCartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findByShoppingCartId(Long id);
}
