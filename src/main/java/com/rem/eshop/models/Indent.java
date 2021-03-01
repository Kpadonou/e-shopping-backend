package com.rem.eshop.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Indent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long datePlaced;

    @OneToOne()
    private ShoppingCart shoppingCart;

    @ManyToOne()
    private User user;

    @Embedded
    private Shipping shipping;

    /*
     * @OneToMany(cascade = CascadeType.ALL)
     * 
     * @JoinColumn(name = "indent_id") private List<ShoppingCartItem> items;
     */

    public Indent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDatePlaced() {
        return this.datePlaced;
    }

    public void setDatePlaced(Long datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Shipping getShipping() {
        return this.shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
