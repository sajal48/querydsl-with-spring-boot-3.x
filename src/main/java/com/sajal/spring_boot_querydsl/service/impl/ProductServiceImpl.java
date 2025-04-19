package com.sajal.spring_boot_querydsl.service.impl;

import com.sajal.spring_boot_querydsl.entity.Product;
import com.sajal.spring_boot_querydsl.repository.ProductRepository;
import com.sajal.spring_boot_querydsl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getProductsByCategoryAndPriceRange(Long categoryId, Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findByCategoryAndPriceRange(categoryId, minPrice, maxPrice, pageable);
    }

    @Override
    public Page<Product> searchProductsByName(String name, Pageable pageable) {
        return productRepository.searchByName(name, pageable);
    }
}