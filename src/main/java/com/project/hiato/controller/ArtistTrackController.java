package com.project.hiato.controller;


import com.project.hiato.dto.ArtistTrackDTO;
import com.project.hiato.entity.ArtistTrack;
import com.project.hiato.entity.ArtistTrackId;
import com.project.hiato.service.ArtistTrackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist_tracks")
public class ArtistTrackController {

    private final ArtistTrackService artistTrackService;

    public ArtistTrackController(ArtistTrackService artistTrackService){
        this.artistTrackService = artistTrackService;
    }

    @PostMapping
    public ArtistTrackDTO create(@RequestBody ArtistTrackDTO data){
        return artistTrackService.create(data);
    }

    @GetMapping
    public List<ArtistTrack> findAll(){
        return artistTrackService.findAll();
    }

    @GetMapping("/{artistId}/{trackId}")
    public Optional<ArtistTrack> findById(@PathVariable Long artistId, @PathVariable Long trackId){
        ArtistTrackId id = new ArtistTrackId(artistId, trackId);
        return artistTrackService.findById(id);
    }

    @DeleteMapping("/{artistId}/{trackId}")
    public void deleteById(@PathVariable Long artistId, @PathVariable Long trackId){
        ArtistTrackId id = new ArtistTrackId(artistId, trackId);
        artistTrackService.deleteById(id);
    }

    @PutMapping("/{artistId}/{trackId}")
    public ArtistTrackDTO update(@PathVariable Long artistId, @PathVariable Long trackId, @RequestBody ArtistTrackDTO data){
        ArtistTrackId id = new ArtistTrackId(artistId, trackId);
        return artistTrackService.update(id, data);
    }
}
