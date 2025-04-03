package com.mironov.sessions_app.repository;

import com.mironov.sessions_app.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity,Long> {
}
