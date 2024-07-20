package com.atharvaworks.novelnook.repository;

import com.atharvaworks.novelnook.model.WishList;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<WishList, ObjectId> {
    WishList findByEmail(String email);
}
