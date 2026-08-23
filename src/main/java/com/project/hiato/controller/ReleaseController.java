package com.project.hiato.controller;

import com.project.hiato.entity.Release;
import com.project.hiato.service.ReleaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleaseController {
    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService){
        this.releaseService = releaseService;
    }

    @PostMapping
    public Release create(@RequestBody Release release){
        return releaseService.create(release);
    }

    @GetMapping
    public List<Release> findAll(){
        return releaseService.findAll();
    }

    @GetMapping("/{id}")
    public Release findById(@PathVariable Long id){
        return releaseService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable Long id){
        releaseService.deleteRelease(id);
    }

    @PutMapping("/{id}")
    public Release updateRelease(@PathVariable Long id, @RequestBody Release release){
        return releaseService.updateRelease(id, release);
    }
}
