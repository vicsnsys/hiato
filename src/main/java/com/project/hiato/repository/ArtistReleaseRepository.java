package com.project.hiato.repository;

import com.project.hiato.entity.ArtistRelease;
import com.project.hiato.entity.ArtistReleaseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistReleaseRepository extends JpaRepository<ArtistRelease, ArtistReleaseId> {
}
