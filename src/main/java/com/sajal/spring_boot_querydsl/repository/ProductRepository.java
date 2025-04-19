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

    default Page<Product> findByFilters(String name, Double minPrice, Double maxPrice, Double minAvgRating, String categoryName, Pageable pageable) {
        QProduct product = QProduct.product;

        BooleanExpression predicate = product.isNotNull(); // Start with a non-null predicate

        if (name != null && !name.isEmpty()) {
            predicate = predicate.and(product.name.containsIgnoreCase(name));
        }
        if (minPrice != null) {
            predicate = predicate.and(product.price.goe(minPrice));
        }
        if (maxPrice != null) {
            predicate = predicate.and(product.price.loe(maxPrice));
        }
        if (minAvgRating != null) {
            predicate = predicate.and(product.avgRating.goe(minAvgRating)); // Use avgRating field
        }
        if (categoryName != null && !categoryName.isEmpty()) {
            predicate = predicate.and(product.category.name.equalsIgnoreCase(categoryName));
        }

        return findAll(predicate, pageable);
    }
}