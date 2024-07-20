package com.atharvaworks.novelnook.repository;

import com.atharvaworks.novelnook.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book , Long> {
    public Book findByBookName(String bookName);

    public List<Book> findAllByBookId(List<Long> bookId);
}
