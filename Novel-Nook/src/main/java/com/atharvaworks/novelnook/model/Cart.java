package com.atharvaworks.novelnook.model;

import com.atharvaworks.novelnook.dto.BookQuantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private ObjectId id;
    private String email;
//    private List<Long> booksId;
    private List<BookQuantity> bookQuantity;
    private String price;
}
