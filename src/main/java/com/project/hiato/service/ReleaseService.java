package com.project.hiato.service;


import com.project.hiato.entity.Release;
import com.project.hiato.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReleaseService {
    ReleaseRepository releaseRepository;

    public ReleaseService(ReleaseRepository releaseRepository){
        this.releaseRepository = releaseRepository;
    }

    public Release create(Release release){
        return releaseRepository.save(release);
    }

    public List<Release> findAll(){
        return releaseRepository.findAll();
    }

    public Optional<Release> findById(Long id){
        return releaseRepository.findById(id);
    }

    public void deleteRelease(Long id){
        releaseRepository.deleteById(id);
    }

    public Release updateRelease(Long id, Release releaseData){
        Release release = releaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Release not found"));

        release.setTitle(releaseData.getTitle());
        release.setRelease_date(releaseData.getRelease_date());
        release.setType(releaseData.getType());

        return releaseRepository.save(release);
    }
}
