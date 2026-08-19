package com.project.hiato.service;

import com.project.hiato.dto.ArtistReleaseDTO;
import com.project.hiato.entity.ArtistRelease;
import com.project.hiato.entity.ArtistReleaseId;
import com.project.hiato.repository.ArtistReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistReleaseService {
    ArtistReleaseRepository artistReleaseRepository;
    public ArtistReleaseService(ArtistReleaseRepository artistReleaseRepository){
        this.artistReleaseRepository = artistReleaseRepository;
    }

    public ArtistReleaseDTO create(ArtistReleaseDTO data){
        ArtistReleaseId id = new ArtistReleaseId(data.getArtistId(), data.getReleaseId());
        ArtistRelease artistRelease = new ArtistRelease();

        artistRelease.setId(id);
        artistRelease.setPrimary(data.isPrimary());

        ArtistRelease saved = artistReleaseRepository.save(artistRelease);

        ArtistReleaseDTO response = new ArtistReleaseDTO();
        response.setArtistId(saved.getId().getArtistId());
        response.setReleaseId(saved.getId().getReleaseId());
        response.setPrimary(saved.isPrimary());
        return response;
    }

    public List<ArtistRelease> findAll(){
        return artistReleaseRepository.findAll();
    }

    public Optional<ArtistRelease> findById(ArtistReleaseId artistReleaseId){
        return artistReleaseRepository.findById(artistReleaseId);
    }

    public void delete (ArtistReleaseId artistReleaseId){
        artistReleaseRepository.deleteById(artistReleaseId);
    }

    public ArtistReleaseDTO updateArtistRelease(
            ArtistReleaseId id,
            ArtistReleaseDTO data) {

        ArtistRelease artistRelease = artistReleaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ArtistRelease not found"));

        artistRelease.setPrimary(data.isPrimary());

        ArtistRelease updated = artistReleaseRepository.save(artistRelease);

        ArtistReleaseDTO response = new ArtistReleaseDTO();

        response.setArtistId(updated.getId().getArtistId());
        response.setReleaseId(updated.getId().getReleaseId());
        response.setPrimary(updated.isPrimary());

        return response;
    }
}
