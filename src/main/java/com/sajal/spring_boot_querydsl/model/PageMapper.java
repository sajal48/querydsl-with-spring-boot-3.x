package com.sajal.spring_boot_querydsl.model;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {
    public <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(page);
    }
}