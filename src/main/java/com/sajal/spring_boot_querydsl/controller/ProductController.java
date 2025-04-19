package com.sajal.spring_boot_querydsl.controller;

import com.sajal.spring_boot_querydsl.entity.Product;
import com.sajal.spring_boot_querydsl.model.PageMapper;
import com.sajal.spring_boot_querydsl.model.PageResponse;
import com.sajal.spring_boot_querydsl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final PageMapper pageMapper;

    @Autowired
    public ProductController(ProductService productService, PageMapper pageMapper) {
        this.productService = productService;
        this.pageMapper = pageMapper;
    }

    @GetMapping("/category/{categoryId}")
    public PageResponse<Product> getProductsByCategoryAndPriceRange(
            @PathVariable Long categoryId,
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            Pageable pageable) {
        Page<Product> products = productService.getProductsByCategoryAndPriceRange(categoryId, minPrice,
                maxPrice, pageable);
        return pageMapper.toPageResponse(products);
    }

    @GetMapping("/search")
    public PageResponse<Product> searchProductsByName(
            @RequestParam String name,
            Pageable pageable) {
        Page<Product> products = productService.searchProductsByName(name, pageable);
        return pageMapper.toPageResponse(products);
    }

    @GetMapping("/filter")
    public PageResponse<Product> getProductsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minAvgRating,
            @RequestParam(required = false) String categoryName,
            Pageable pageable) {
        Page<Product> products = productService.getProductsByFilters(name, minPrice, maxPrice, minAvgRating,
                categoryName, pageable);
        return pageMapper.toPageResponse(products);
    }
}