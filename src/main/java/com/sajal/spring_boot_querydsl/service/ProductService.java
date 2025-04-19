package com.sajal.spring_boot_querydsl.service;

import com.sajal.spring_boot_querydsl.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getProductsByCategoryAndPriceRange(Long categoryId, Double minPrice, Double maxPrice, Pageable pageable);
    Page<Product> searchProductsByName(String name, Pageable pageable);
    Page<Product> getProductsByFilters(String name, Double minPrice, Double maxPrice, Double minAvgRating, String categoryName, Pageable pageable);
}