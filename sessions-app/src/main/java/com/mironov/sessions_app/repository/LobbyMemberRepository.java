package com.mironov.sessions_app.repository;

import com.mironov.sessions_app.entity.LobbyEntity;
import com.mironov.sessions_app.entity.LobbyMemberEntity;
import com.mironov.sessions_app.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LobbyMemberRepository extends JpaRepository<LobbyMemberEntity,Long> {
    boolean existsByUserAndLobby(UserEntity user, LobbyEntity lobby);

    boolean existsByUser(UserEntity user);

    @Query("SELECT lm.user FROM LobbyMemberEntity lm WHERE lm.lobby = :lobby")
    List<UserEntity> findUsersByLobby(@Param("lobby") LobbyEntity lobby);

    @Modifying
    @Transactional
    @Query("DELETE FROM LobbyMemberEntity lm WHERE lm.user.id = :id")
    void deleteUserById(@Param("id") Long userId);

    @Query("SELECT lm.lobby FROM LobbyMemberEntity lm WHERE lm.user = :user AND lm.lobby = :lobby")
    LobbyEntity findLobbyByUser(@Param("user") UserEntity user);

    Long countByLobby(@Param("lobby") LobbyEntity lobby);
}
