package com.project.hiato.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistTrackDTO {
    private Long artistId;
    private Long trackId;
    private boolean isPrimary;
}
