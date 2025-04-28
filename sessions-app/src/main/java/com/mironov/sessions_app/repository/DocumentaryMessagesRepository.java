package com.mironov.sessions_app.repository;

import com.mironov.sessions_app.entity.DocumentaryMessagesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentaryMessagesRepository extends MongoRepository<DocumentaryMessagesEntity,String> {
}
