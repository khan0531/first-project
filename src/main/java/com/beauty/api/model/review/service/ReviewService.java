package com.beauty.api.model.review.service;

import com.beauty.api.model.review.persist.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
}
