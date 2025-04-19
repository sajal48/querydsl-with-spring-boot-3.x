package com.sajal.spring_boot_querydsl.repository;

import com.sajal.spring_boot_querydsl.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
    // Add custom methods if needed
}