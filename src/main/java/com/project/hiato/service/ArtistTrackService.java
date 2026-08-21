package com.project.hiato.service;

import com.project.hiato.dto.ArtistTrackDTO;
import com.project.hiato.entity.ArtistTrack;
import com.project.hiato.entity.ArtistTrackId;
import com.project.hiato.repository.ArtistTrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistTrackService {
    private final ArtistTrackRepository artistTrackRepository;

    public ArtistTrackService(ArtistTrackRepository artistTrackRepository){
        this.artistTrackRepository = artistTrackRepository;
    }

    public ArtistTrackDTO create(ArtistTrackDTO data){
        ArtistTrackId artistTrackId = new ArtistTrackId(data.getArtistId(), data.getTrackId());
        ArtistTrack artistTrack = new ArtistTrack();

        artistTrack.setArtistTrackId(artistTrackId);
        artistTrack.setPrimary(data.isPrimary());

        ArtistTrack saved = artistTrackRepository.save(artistTrack);

        ArtistTrackDTO response = new ArtistTrackDTO();
        response.setTrackId(saved.getArtistTrackId().getTrackId());
        response.setArtistId(saved.getArtistTrackId().getArtistId());
        response.setPrimary(saved.isPrimary());
        return response;
    }

    public List<ArtistTrack> findAll(){
        return artistTrackRepository.findAll();
    }

    public Optional<ArtistTrack> findById(ArtistTrackId artistTrackId){
        return artistTrackRepository.findById(artistTrackId);
    }

    public void deleteById(ArtistTrackId artistTrackId){
        artistTrackRepository.deleteById(artistTrackId);
    }

    public ArtistTrackDTO update(ArtistTrackId artistTrackId, ArtistTrackDTO data){
        ArtistTrack artistTrack = artistTrackRepository.findById(artistTrackId)
                .orElseThrow( () -> new RuntimeException("Artist Track not found"));

        artistTrack.setPrimary(data.isPrimary());

        ArtistTrack updated = artistTrackRepository.save(artistTrack);

        ArtistTrackDTO response = new ArtistTrackDTO();

        response.setPrimary(updated.isPrimary());
        response.setArtistId(updated.getArtistTrackId().getArtistId());
        response.setTrackId(updated.getArtistTrackId().getTrackId());

        return response;

    }
}
