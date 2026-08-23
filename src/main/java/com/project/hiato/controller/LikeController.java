package com.project.hiato.controller;

import com.project.hiato.dto.LikeDTO;
import com.project.hiato.entity.LikeId;
import com.project.hiato.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping
    public LikeDTO create(@RequestBody LikeDTO data){
        return likeService.create(data);
    }

    @GetMapping
    public List<LikeDTO> findAll(){
        return likeService.findAll();
    }

    @GetMapping("/{userId}/{reviewId}")
    public LikeDTO findById(@PathVariable Long userId, @PathVariable Long reviewId){
        LikeId id = new LikeId();
        id.setUserId(userId);
        id.setReviewId(reviewId);
        return likeService.findById(id);
    }

    @DeleteMapping("/{userId}/{reviewId}")
    public void deleteById(@PathVariable Long userId, @PathVariable Long reviewId){
        LikeId id = new LikeId();
        id.setUserId(userId);
        id.setReviewId(reviewId);
        likeService.deleteById(id);
    }

}
