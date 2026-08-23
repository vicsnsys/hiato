package com.project.hiato.controller;

import com.project.hiato.dto.ReviewDTO;
import com.project.hiato.dto.ReviewResponseDTO;
import com.project.hiato.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewResponseDTO create(@Valid @RequestBody ReviewDTO data){
        return reviewService.create(data);
    }

    @GetMapping
    public List<ReviewResponseDTO> findAll(){
        return reviewService.findAll();
    }


    @GetMapping("/{id}")
    public ReviewResponseDTO findById(@PathVariable Long id){
        return reviewService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        reviewService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ReviewResponseDTO update(@PathVariable Long id, @RequestBody ReviewDTO data){
        return reviewService.update(id, data);
    }


}
