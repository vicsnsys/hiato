package com.project.hiato.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDTO {
    private Long id;
    private Long userId;
    private String description;
    private Integer stars;
    private String typeReview;
    private Long targetId;
}
