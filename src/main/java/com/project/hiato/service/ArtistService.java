package com.project.hiato.service;

import com.project.hiato.entity.Artist;
import com.project.hiato.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public Artist create(Artist artist){
        return artistRepository.save(artist);
    }

    public List<Artist> findAll(){
        return artistRepository.findAll();
    }

    public Optional<Artist> findById(Long id){
        return artistRepository.findById(id);
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    public Artist updateArtist(Long id, Artist artistData){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        artist.setName(artistData.getName());
        artist.setBiography(artistData.getBiography());

        return artistRepository.save(artist);
    }

}
