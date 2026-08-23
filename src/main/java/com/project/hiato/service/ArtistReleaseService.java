package com.project.hiato.service;

import com.project.hiato.dto.ArtistReleaseDTO;
import com.project.hiato.entity.ArtistRelease;
import com.project.hiato.entity.ArtistReleaseId;
import com.project.hiato.exception.ConflictException;
import com.project.hiato.exception.ResourceNotFoundException;
import com.project.hiato.repository.ArtistReleaseRepository;
import com.project.hiato.repository.ArtistRepository;
import com.project.hiato.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistReleaseService {
    private final ArtistReleaseRepository artistReleaseRepository;
    private final ArtistRepository artistRepository;
    private final ReleaseRepository releaseRepository;

    public ArtistReleaseService(ArtistReleaseRepository artistReleaseRepository, ArtistRepository artistRepository, ReleaseRepository releaseRepository){
        this.artistReleaseRepository = artistReleaseRepository;
        this.artistRepository = artistRepository;
        this.releaseRepository = releaseRepository;
    }

    public ArtistReleaseDTO create(ArtistReleaseDTO data){
        ArtistReleaseId id = new ArtistReleaseId(data.getArtistId(), data.getReleaseId());
        ArtistRelease artistRelease = new ArtistRelease();

        if(!artistRepository.existsById(data.getArtistId())){
            throw new ResourceNotFoundException("Artist not found");
        }

        if(!releaseRepository.existsById(data.getReleaseId())){
            throw new ResourceNotFoundException("Release not found");
        }

        if(artistReleaseRepository.existsById(id)){
            throw new ConflictException("Artist Release already exists");
        }


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

    public ArtistRelease findById(ArtistReleaseId artistReleaseId){
        return artistReleaseRepository.findById(artistReleaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist Release not found"));
    }

    public void delete (ArtistReleaseId artistReleaseId){
        if(!artistReleaseRepository.existsById(artistReleaseId)){
            throw new ResourceNotFoundException("Artist Release not found");
        }
        artistReleaseRepository.deleteById(artistReleaseId);
    }

    public ArtistReleaseDTO updateArtistRelease(
            ArtistReleaseId id,
            ArtistReleaseDTO data) {

        if(!artistRepository.existsById(id.getArtistId())){
            throw new ResourceNotFoundException("Artist not found");
        }

        if(!releaseRepository.existsById(id.getReleaseId())){
            throw new ResourceNotFoundException("Release not found");
        }

        ArtistRelease artistRelease = artistReleaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist Release not found"));

        artistRelease.setPrimary(data.isPrimary());

        ArtistRelease updated = artistReleaseRepository.save(artistRelease);

        ArtistReleaseDTO response = new ArtistReleaseDTO();

        response.setArtistId(updated.getId().getArtistId());
        response.setReleaseId(updated.getId().getReleaseId());
        response.setPrimary(updated.isPrimary());

        return response;
    }
}
