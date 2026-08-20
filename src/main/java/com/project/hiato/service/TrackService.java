package com.project.hiato.service;

import com.project.hiato.entity.Track;
import com.project.hiato.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {
    TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository){
        this.trackRepository = trackRepository;
    }

    public Track create(Track data){
        return trackRepository.save(data);
    }

    public List<Track> findAll(){
        return trackRepository.findAll();
    }

    public Optional<Track> findById(Long id){
        return trackRepository.findById(id);
    }

    public void deleteById(Long id){
        trackRepository.deleteById(id);
    }

    public Track updateTrack(Long id, Track data){
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track not found"));

        track.setName(data.getName());
        track.setReleaseId(data.getReleaseId());

        return trackRepository.save(track);
    }
}
