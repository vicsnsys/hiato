package com.project.hiato.dto;

import com.project.hiato.utils.TypeReview;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private Long userId;
    private String description;
    private Integer stars;

    @NotNull
    private TypeReview typeReview;
    private Long targetId;
}
