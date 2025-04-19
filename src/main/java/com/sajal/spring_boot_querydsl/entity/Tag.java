package com.sajal.spring_boot_querydsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Product> products;

    public void addProduct(Product product) {
        this.products.add(product);
        product.getTags().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getTags().remove(this);
    }
}