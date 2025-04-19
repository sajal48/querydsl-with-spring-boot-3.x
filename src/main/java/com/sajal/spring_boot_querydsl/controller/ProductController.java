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
}