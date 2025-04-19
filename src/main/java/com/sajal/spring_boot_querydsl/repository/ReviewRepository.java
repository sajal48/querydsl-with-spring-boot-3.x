package com.sajal.spring_boot_querydsl.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sajal.spring_boot_querydsl.entity.QReview;
import com.sajal.spring_boot_querydsl.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends BaseRepository<Review, Long> {

    default Page<Review> findByRatingRange(Integer minRating, Integer maxRating, Pageable pageable) {
        QReview review = QReview.review;
        BooleanExpression predicate = review.rating.between(minRating, maxRating);
        return findAll(predicate, pageable);
    }
}