package com.sajal.spring_boot_querydsl.repository;

import com.sajal.spring_boot_querydsl.entity.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends BaseRepository<Tag, Long> {
    // Add custom methods if needed
}