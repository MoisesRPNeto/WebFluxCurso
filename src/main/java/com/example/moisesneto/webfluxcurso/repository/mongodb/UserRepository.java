package com.example.moisesneto.webfluxcurso.repository.mongodb;

import com.example.moisesneto.webfluxcurso.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final ReactiveMongoTemplate templateMongo;

    public Mono<User> save(final User user){

        return templateMongo.save(user);
    }

    public Mono<User> findById(String id) {
        return templateMongo.findById(id, User.class);
    }

    public Flux<User> findall() {
        return templateMongo.findAll(User.class);
    }


    public Mono<User> findAndRemover(String id) {
        Query query = new Query();
        Criteria where = Criteria.where("id").is(id);
        return templateMongo.findAndRemove(query.addCriteria(where), User.class);
    }
}
