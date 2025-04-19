package com.sajal.spring_boot_querydsl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sajal.spring_boot_querydsl.exception.InvalidRatingException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    // Getters and Setters
    public void updateRating(Integer newRating) {
        if (newRating < 1 || newRating > 5) {
            throw new InvalidRatingException("Rating must be between 1 and 5.");
        }
        this.rating = newRating;
    }
}