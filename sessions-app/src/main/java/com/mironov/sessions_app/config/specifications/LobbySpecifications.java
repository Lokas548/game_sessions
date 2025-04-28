package com.mironov.sessions_app.config.specifications;

import com.mironov.sessions_app.entity.LobbyEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class LobbySpecifications {
    public static Specification<LobbyEntity> hasName(String lobbyName){
        return ((root, query, criteriaBuilder) -> {
            if(lobbyName == null || lobbyName.isEmpty())
                return criteriaBuilder.conjunction();
            return criteriaBuilder.like(root.get("name"),"%" + lobbyName + "%");
        });
    }

    public static Specification<LobbyEntity> hasGames(List<Long> gamesIdsList){
        return (((root, query, criteriaBuilder) -> {
            if(gamesIdsList == null || gamesIdsList.isEmpty())
                return criteriaBuilder.conjunction();
            return root.get("gameId").in(gamesIdsList);
        }));
    }

    public static Specification<LobbyEntity> hasCompetitive(Boolean isCompetitive){
        return (root, query, criteriaBuilder) -> {
            if(isCompetitive == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("isCompetitive"), isCompetitive);
        };
    }
}
