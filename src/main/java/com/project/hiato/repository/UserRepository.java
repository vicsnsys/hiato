package com.project.hiato.repository;

import com.project.hiato.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String nickname);

    boolean existsByNicknameAndIdNot(String nickname, Long id);
}
