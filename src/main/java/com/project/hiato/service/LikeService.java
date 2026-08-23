package com.project.hiato.service;

import com.project.hiato.dto.LikeDTO;
import com.project.hiato.entity.Like;
import com.project.hiato.entity.LikeId;
import com.project.hiato.repository.LikeRepository;
import com.project.hiato.repository.ReviewRepository;
import com.project.hiato.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, ReviewRepository reviewRepository){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public LikeDTO create(LikeDTO data){
        LikeId id = new LikeId();
        id.setUserId(data.getUserId());
        id.setReviewId(data.getReviewId());

        if(!userRepository.existsById(id.getUserId())){
            throw new RuntimeException("User not found");
        }

        if(!reviewRepository.existsById(id.getReviewId())){
            throw new RuntimeException("Review not found");
        }

        if(likeRepository.existsById(id)){
            throw new RuntimeException("Like already exists");
        }

        Like like = new Like();
        like.setId(id);
        like = likeRepository.save(like);
        LikeDTO response = new LikeDTO();
        response.setUserId(like.getId().getUserId());
        response.setReviewId(like.getId().getReviewId());

        return response;
    }

    public List<LikeDTO> findAll(){
        List<Like> likes = likeRepository.findAll();
        List<LikeDTO> response = new ArrayList<>();

        for(Like like : likes){
            LikeDTO dto = new LikeDTO();
            dto.setUserId(like.getId().getUserId());
            dto.setReviewId(like.getId().getReviewId());
            response.add(dto);
        }

        return response;
    }

    public LikeDTO findById(LikeId id){
        Like like = likeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like not found"));
        LikeDTO response = new LikeDTO();
        response.setUserId(like.getId().getUserId());
        response.setReviewId(like.getId().getReviewId());

        return response;
    }

    public void deleteById(LikeId id){
        if(!likeRepository.existsById(id)){
            throw new RuntimeException("Like not found");
        }
        likeRepository.deleteById(id);
    }

}
