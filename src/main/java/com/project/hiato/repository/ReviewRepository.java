package com.project.hiato.repository;

import com.project.hiato.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserIdAndTypeReviewAndTargetId(Long userId, String typeReview, Long targetId);
}
