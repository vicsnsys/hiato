package com.project.hiato.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name="artist_tracks")
public class ArtistTrack {

    @EmbeddedId
    private ArtistTrackId artistTrackId;

    @Column(name="is_primary", nullable = false)
    private boolean isPrimary;
}
