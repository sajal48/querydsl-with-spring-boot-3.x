package com.sajal.spring_boot_querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;
import com.querydsl.core.types.dsl.EntityPathBase;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T>, QuerydslBinderCustomizer<EntityPathBase<T>> {

    @Override
    default void customize(QuerydslBindings bindings, EntityPathBase<T> root) {
        // Customize bindings if needed
    }
}