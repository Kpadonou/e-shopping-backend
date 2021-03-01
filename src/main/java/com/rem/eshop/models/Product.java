package com.rem.eshop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private float price;

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne()
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;

    /*
     * @OneToOne(mappedBy = "product") private ShoppingCartItem shoppingCartItem;
     */

    public Product() {
    }

    public Product(String title, float price, Category category, String imageUrl) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
