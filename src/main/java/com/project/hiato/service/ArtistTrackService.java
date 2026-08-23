package com.project.hiato.service;

import com.project.hiato.dto.ArtistTrackDTO;
import com.project.hiato.entity.ArtistTrack;
import com.project.hiato.entity.ArtistTrackId;

import com.project.hiato.exception.ConflictException;
import com.project.hiato.exception.ResourceNotFoundException;
import com.project.hiato.repository.ArtistRepository;
import com.project.hiato.repository.ArtistTrackRepository;
import com.project.hiato.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistTrackService {
    private final ArtistTrackRepository artistTrackRepository;
    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;

    public ArtistTrackService(ArtistTrackRepository artistTrackRepository, ArtistRepository artistRepository, TrackRepository trackRepository){
        this.artistTrackRepository = artistTrackRepository;
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    public ArtistTrackDTO create(ArtistTrackDTO data){

        ArtistTrackId artistTrackId = new ArtistTrackId(data.getArtistId(), data.getTrackId());
        ArtistTrack artistTrack = new ArtistTrack();

        if(!artistRepository.existsById(data.getArtistId())){
            throw new ResourceNotFoundException("Artist not found");
        }

        if(!trackRepository.existsById(data.getTrackId())){
            throw new ResourceNotFoundException("Track not found");
        }

        if(artistTrackRepository.existsById(artistTrackId)){
            throw new ConflictException("Artist Track already exists");
        }

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

    public ArtistTrack findById(ArtistTrackId artistTrackId){
        return artistTrackRepository.findById(artistTrackId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist Track not found"));
    }

    public void deleteById(ArtistTrackId artistTrackId){
        if(!artistTrackRepository.existsById(artistTrackId)){
            throw new ResourceNotFoundException("Artist Track not found");
        }
        artistTrackRepository.deleteById(artistTrackId);
    }

    public ArtistTrackDTO update(ArtistTrackId artistTrackId, ArtistTrackDTO data){

        if(!artistRepository.existsById(artistTrackId.getArtistId())){
            throw new ResourceNotFoundException("Artist not found");
        }

        if(!trackRepository.existsById(artistTrackId.getTrackId())){
            throw new ResourceNotFoundException("Track not found");
        }

        ArtistTrack artistTrack = artistTrackRepository.findById(artistTrackId)
                .orElseThrow( () -> new ResourceNotFoundException("Artist Track not found"));

        artistTrack.setPrimary(data.isPrimary());

        ArtistTrack updated = artistTrackRepository.save(artistTrack);

        ArtistTrackDTO response = new ArtistTrackDTO();

        response.setPrimary(updated.isPrimary());
        response.setArtistId(updated.getArtistTrackId().getArtistId());
        response.setTrackId(updated.getArtistTrackId().getTrackId());

        return response;

    }
}
