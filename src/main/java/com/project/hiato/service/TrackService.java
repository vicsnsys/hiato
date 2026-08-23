package com.project.hiato.service;

import com.project.hiato.entity.Track;
import com.project.hiato.exception.ResourceNotFoundException;
import com.project.hiato.repository.ReleaseRepository;
import com.project.hiato.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    private final TrackRepository trackRepository;
    private final ReleaseRepository releaseRepository;


    public TrackService(TrackRepository trackRepository, ReleaseRepository releaseRepository){
        this.trackRepository = trackRepository;
        this.releaseRepository = releaseRepository;
    }

    public Track create(Track data){
        if(!releaseRepository.existsById(data.getReleaseId())){
            throw new ResourceNotFoundException("Release not found");
        }
        return trackRepository.save(data);
    }

    public List<Track> findAll(){
        return trackRepository.findAll();
    }

    public Track findById(Long id){
        return trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));
    }

    public void deleteById(Long id){
        if(!trackRepository.existsById(id)){
            throw new ResourceNotFoundException("Track not found");
        }
        trackRepository.deleteById(id);
    }

    public Track updateTrack(Long id, Track data){
        if(!releaseRepository.existsById(data.getReleaseId())){
            throw new ResourceNotFoundException("Release not found");
        }
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found"));

        track.setName(data.getName());
        track.setReleaseId(data.getReleaseId());

        return trackRepository.save(track);
    }
}
