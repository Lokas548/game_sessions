package com.mironov.sessions_app.repository;


import com.mironov.sessions_app.DTO.response.FilteredLobbiesResponse;
import com.mironov.sessions_app.entity.LobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LobbyRepository extends JpaRepository<LobbyEntity,Long> {
    Optional<LobbyEntity> findById(Long id);
    List<LobbyEntity> findAll();
}
