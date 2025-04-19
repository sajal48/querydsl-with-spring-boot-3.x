package com.sajal.spring_boot_querydsl.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sajal.spring_boot_querydsl.exception.InvalidPriceException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "product_tag",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    private Double avgRating = 0.0;

    // Core logic
    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getProducts().add(this);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getProducts().remove(this);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setProduct(this);
        updateAvgRating();
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        review.setProduct(null);
        updateAvgRating();
    }

    public void updatePrice(Double newPrice) {
        if (newPrice <= 0) {
            throw new InvalidPriceException("Price must be greater than zero.");
        }
        this.price = newPrice;
    }

    private void updateAvgRating() {
        if (reviews.isEmpty()) {
            this.avgRating = 0.0;
        } else {
            this.avgRating = reviews.stream()
                    .mapToDouble(Review::getRating)
                    .average()
                    .orElse(0.0);
        }
    }

}