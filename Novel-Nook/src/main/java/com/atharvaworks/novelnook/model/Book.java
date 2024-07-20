package com.atharvaworks.novelnook.model;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "books")
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    private long bookId;
    private String bookName;
    private  String bookDescription;
    private String bookPrice;
    private String bookAuthor;
    private String category;
    private int bookQuantity;
}
