package com.project.hiato.repository;

import com.project.hiato.entity.Like;
import com.project.hiato.entity.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, LikeId> {

}
