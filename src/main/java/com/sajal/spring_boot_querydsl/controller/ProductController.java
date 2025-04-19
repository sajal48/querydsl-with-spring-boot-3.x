package com.sajal.spring_boot_querydsl.controller;

import com.sajal.spring_boot_querydsl.entity.Product;
import com.sajal.spring_boot_querydsl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{categoryId}")
    public Page<Product> getProductsByCategoryAndPriceRange(
            @PathVariable Long categoryId,
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            Pageable pageable) {
        return productService.getProductsByCategoryAndPriceRange(categoryId, minPrice, maxPrice, pageable);
    }

    @GetMapping("/search")
    public Page<Product> searchProductsByName(
            @RequestParam String name,
            Pageable pageable) {
        return productService.searchProductsByName(name, pageable);
    }

    @GetMapping("/filter")
    public Page<Product> getProductsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minAvgRating,
            @RequestParam(required = false) String categoryName,
            Pageable pageable) {
        return productService.getProductsByFilters(name, minPrice, maxPrice, minAvgRating, categoryName, pageable);
    }
}