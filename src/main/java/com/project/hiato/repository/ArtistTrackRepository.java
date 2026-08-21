package com.project.hiato.repository;

import com.project.hiato.entity.ArtistTrack;
import com.project.hiato.entity.ArtistTrackId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistTrackRepository extends JpaRepository<ArtistTrack, ArtistTrackId> {
}
