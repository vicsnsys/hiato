package com.project.hiato.service;

import com.project.hiato.dto.ReviewDTO;
import com.project.hiato.dto.ReviewResponseDTO;
import com.project.hiato.entity.Review;
import com.project.hiato.utils.TypeReview;
import com.project.hiato.repository.ReleaseRepository;
import com.project.hiato.repository.ReviewRepository;
import com.project.hiato.repository.TrackRepository;
import com.project.hiato.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TrackRepository trackRepository;
    private final ReleaseRepository releaseRepository;




    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository,
                         TrackRepository trackRepository, ReleaseRepository releaseRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.trackRepository = trackRepository;
        this.releaseRepository = releaseRepository;
    }

    public ReviewResponseDTO create(ReviewDTO data){
        Review review = new Review();

        if(!userRepository.existsById(data.getUserId())){
            throw new RuntimeException("User not found");
        }

        if(data.getStars() < 1 || data.getStars() > 5) {
            throw new ArithmeticException("Stars should be in a range 1-5");
        }

        if(!data.getTypeReview().equals(TypeReview.TRACK) && !data.getTypeReview().equals(TypeReview.RELEASE)){
            throw new RuntimeException("Type Review should be a RELEASE or a TRACK");
        }

        if(data.getTypeReview().equals(TypeReview.RELEASE) && !releaseRepository.existsById(data.getTargetId())){
            throw new RuntimeException("Release not found");
        }

        if(data.getTypeReview().equals(TypeReview.TRACK) && !trackRepository.existsById(data.getTargetId())){
            throw new RuntimeException("Track not found");
        }

        review.setUserId(data.getUserId());
        review.setDescription(data.getDescription());
        review.setStars(data.getStars());
        review.setTypeReview(data.getTypeReview().name());
        review.setTargetId(data.getTargetId());

        Review saved = reviewRepository.save(review);


        return toResponseDTO(saved);
    }

    public List<ReviewResponseDTO> findAll(){
        List<Review> reviews = reviewRepository.findAll();

        List<ReviewResponseDTO> response = new ArrayList<>();

        for(Review review : reviews){
            response.add(toResponseDTO(review));
        }

        return response;
    }

    public ReviewResponseDTO findById(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return toResponseDTO(review);
    }

    public void deleteById(Long id){
        if(!reviewRepository.existsById(id)){
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    public ReviewResponseDTO update(Long id, ReviewDTO data){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if(data.getStars() < 1 || data.getStars() > 5){
            throw new ArithmeticException("Stars should be in a range 1-5");
        }

        review.setStars(data.getStars());
        review.setDescription(data.getDescription());

        Review updated = reviewRepository.save(review);

        return toResponseDTO(updated);

    }

    private ReviewResponseDTO toResponseDTO(Review data){
        ReviewResponseDTO response = new ReviewResponseDTO();
        response.setId(data.getId());
        response.setUserId(data.getUserId());
        response.setStars(data.getStars());
        response.setDescription(data.getDescription());
        response.setTypeReview(data.getTypeReview());
        response.setTargetId(data.getTargetId());

        return response;
    }
}
