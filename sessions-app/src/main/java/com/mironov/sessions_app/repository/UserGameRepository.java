package com.mironov.sessions_app.repository;


import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.entity.UserGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameRepository extends JpaRepository<UserGameEntity,Long> {
    List<UserGameEntity> findByUser(UserEntity user);
}
