package com.project.hiato.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistReleaseDTO {
    private Long artistId;
    private Long releaseId;
    private boolean isPrimary;
}
