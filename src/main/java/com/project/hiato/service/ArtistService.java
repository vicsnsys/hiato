package com.project.hiato.service;

import com.project.hiato.entity.Artist;
import com.project.hiato.exception.ResourceNotFoundException;
import com.project.hiato.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Artist findById(Long id){
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
    }

    public void deleteArtist(Long id) {
        if(!artistRepository.existsById(id)){
            throw new ResourceNotFoundException("Artist not found");
        }
        artistRepository.deleteById(id);
    }

    public Artist updateArtist(Long id, Artist artistData){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

        artist.setName(artistData.getName());
        artist.setBiography(artistData.getBiography());

        return artistRepository.save(artist);
    }

}
