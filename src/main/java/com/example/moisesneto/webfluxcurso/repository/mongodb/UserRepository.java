package com.example.moisesneto.webfluxcurso.repository.mongodb;

import com.example.moisesneto.webfluxcurso.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
