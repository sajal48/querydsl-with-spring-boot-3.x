package com.sajal.spring_boot_querydsl.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sajal.spring_boot_querydsl.entity.Product;
import com.sajal.spring_boot_querydsl.entity.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    default Page<Product> findByCategoryAndPriceRange(Long categoryId, Double minPrice, Double maxPrice, Pageable pageable) {
        QProduct product = QProduct.product;
        BooleanExpression predicate = product.category.id.eq(categoryId)
                .and(product.price.between(minPrice, maxPrice));
        return findAll(predicate, pageable);
    }

    default Page<Product> searchByName(String name, Pageable pageable) {
        QProduct product = QProduct.product;
        BooleanExpression predicate = product.name.containsIgnoreCase(name);
        return findAll(predicate, pageable);
    }
}