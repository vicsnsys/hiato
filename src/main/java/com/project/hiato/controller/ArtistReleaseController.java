package com.project.hiato.controller;


import com.project.hiato.dto.ArtistReleaseDTO;
import com.project.hiato.entity.ArtistRelease;
import com.project.hiato.entity.ArtistReleaseId;
import com.project.hiato.service.ArtistReleaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist_releases")
public class ArtistReleaseController {
    private final ArtistReleaseService artistReleaseService;

    public ArtistReleaseController(ArtistReleaseService artistReleaseService){
        this.artistReleaseService = artistReleaseService;
    }

    @PostMapping
    public ArtistReleaseDTO create(@RequestBody ArtistReleaseDTO data){
        return artistReleaseService.create(data);
    }

    @GetMapping
    public List<ArtistRelease> findAll(){
        return artistReleaseService.findAll();
    }

    @GetMapping("/{artistId}/{releaseId}")
    public ArtistRelease findById(@PathVariable Long artistId, @PathVariable Long releaseId){
        ArtistReleaseId artistReleaseId = new ArtistReleaseId(artistId, releaseId);
        return artistReleaseService.findById(artistReleaseId);
    }

    @DeleteMapping("/{artistId}/{releaseId}")
    public void delete(@PathVariable Long artistId, @PathVariable Long releaseId){
        ArtistReleaseId artistReleaseId = new ArtistReleaseId(artistId, releaseId);
        artistReleaseService.delete(artistReleaseId);
    }

    @PutMapping("/{artistId}/{releaseId}")
    public ArtistReleaseDTO update(@PathVariable Long artistId, @PathVariable Long releaseId, @RequestBody ArtistReleaseDTO dataArtistRelease){
        ArtistReleaseId artistReleaseId = new ArtistReleaseId(artistId, releaseId);
        return artistReleaseService.updateArtistRelease(artistReleaseId, dataArtistRelease);
    }
}
