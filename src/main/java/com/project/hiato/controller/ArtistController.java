package com.project.hiato.controller;

import com.project.hiato.entity.Artist;
import com.project.hiato.service.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @PostMapping
    public Artist create(@RequestBody Artist artist) {
        return artistService.create(artist);
    }

    @GetMapping
    public List<Artist> findAll(){
        return artistService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Artist> findById(@PathVariable Long id){
        return artistService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
    }

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        return artistService.updateArtist(id, artist);
    }
}
