package com.beauty.api.model.review.persist.repository;

import com.beauty.api.model.review.persist.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

}
