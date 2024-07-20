package com.atharvaworks.novelnook.model;

import com.atharvaworks.novelnook.dto.BookQuantity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "order")
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";

    private String email;
    @Id
    private long orderId;
    private List<BookQuantity> bookList;
    private String Address;
    private String price;
    private String paymentMode;
//    private String state;
}
