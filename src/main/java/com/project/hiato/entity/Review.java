package com.project.hiato.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer stars;
    @Column(name="type_review", nullable = false)
    private String typeReview;
    @Column(name="target_id", nullable = false)
    private Long targetId;
}
