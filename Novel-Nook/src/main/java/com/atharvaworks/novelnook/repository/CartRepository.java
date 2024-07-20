package com.atharvaworks.novelnook.repository;

import com.atharvaworks.novelnook.model.Cart;
import com.atharvaworks.novelnook.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart , ObjectId> {
    public Cart findByEmail(String email);
}
