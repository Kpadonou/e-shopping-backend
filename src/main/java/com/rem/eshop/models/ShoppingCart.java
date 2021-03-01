package com.rem.eshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL) private
     * List<ShoppingCartItem> ShoppingCartItems;
     */

    public ShoppingCart() {
    }

    /*
     * public ShoppingCart(List<ShoppingCartItem> ShoppingCartItems) {
     * this.ShoppingCartItems = ShoppingCartItems; }
     * 
     * public List<ShoppingCartItem> getShoppingCartItems() { return
     * this.ShoppingCartItems; }
     * 
     * public void setShoppingCartItems(List<ShoppingCartItem> ShoppingCartItems) {
     * this.ShoppingCartItems = ShoppingCartItems; }
     */

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
