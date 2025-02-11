package com.atharvaworks.novelnook.repository;

import com.atharvaworks.novelnook.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    public User findByEmail(String email);

}
