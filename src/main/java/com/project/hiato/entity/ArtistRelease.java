package com.project.hiato.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="artist_releases")
@Getter
@Setter
public class ArtistRelease {
    @EmbeddedId
    private ArtistReleaseId id;

    @Column(name="is_primary", nullable = false)
    private boolean isPrimary;
}
