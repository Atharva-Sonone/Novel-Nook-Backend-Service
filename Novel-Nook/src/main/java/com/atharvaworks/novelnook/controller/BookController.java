package com.atharvaworks.novelnook.controller;

import com.atharvaworks.novelnook.dto.book.AddBookDto;
import com.atharvaworks.novelnook.dto.book.BookDto;
import com.atharvaworks.novelnook.model.Book;
import com.atharvaworks.novelnook.service.BookService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@Valid @RequestBody AddBookDto addBookDto) throws BadRequestException {
        return new ResponseEntity<String>(bookService.addBook(addBookDto), HttpStatus.CREATED);
    }
    @GetMapping("/all-books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<List<BookDto>>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<BookDto> getBookByName(@PathVariable String name){
        return new ResponseEntity<BookDto>(bookService.getBookByName(name),HttpStatus.OK);
    }
    @PutMapping("/update-book/{name}")
    public ResponseEntity<BookDto> updateBook(@RequestBody AddBookDto addBookDto, @PathVariable String name){
        return new ResponseEntity<BookDto>(bookService.updateBook(addBookDto , name),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-by-name/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name){
        return new ResponseEntity<String>(bookService.deleteBookByName(name), HttpStatus.OK);
    }
}
