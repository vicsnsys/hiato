package com.project.hiato.controller;

import com.project.hiato.entity.Track;
import com.project.hiato.service.TrackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {
    private final TrackService trackService;
    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }

    @PostMapping
    public Track create(@RequestBody Track data){
        return trackService.create(data);
    }

    @GetMapping
    public List<Track> findAll(){
        return trackService.findAll();
    }

    @GetMapping("/{id}")
    public Track findById(@PathVariable Long id){
        return trackService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        trackService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Track update(@PathVariable Long id, @RequestBody Track data){
        return trackService.updateTrack(id, data);
    }
}
