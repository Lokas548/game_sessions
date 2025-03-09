package com.mironov.sessions_app.repository;

import com.mironov.sessions_app.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity,Long> {
}
