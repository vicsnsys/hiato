package com.project.hiato.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ArtistTrackId {
    @NotNull
    @Column(name = "artist_id", nullable = false)
    private Long artistId;

    @NotNull
    @Column(name = "track_id", nullable = false)
    private Long trackId;
}
