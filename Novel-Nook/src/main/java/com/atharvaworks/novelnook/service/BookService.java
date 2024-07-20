package com.atharvaworks.novelnook.service;

import com.atharvaworks.novelnook.dto.book.AddBookDto;
import com.atharvaworks.novelnook.dto.book.BookDto;
import com.atharvaworks.novelnook.exception.BadRequestException;
import com.atharvaworks.novelnook.model.Book;
import com.atharvaworks.novelnook.model.DatabaseSequence;
import com.atharvaworks.novelnook.repository.BookRepository;
import com.atharvaworks.novelnook.utility.DtoMapping;
import com.atharvaworks.novelnook.utility.GenerateSequence;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
@Service
public class BookService {

    @Autowired
    GenerateSequence generateSequence;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    DtoMapping dtoMapping;

    public String addBook(AddBookDto addBookDto) throws BadRequestException {
        Book book =bookRepository.findByBookName(addBookDto.getBookName());
        if(null != book)
        {
         throw new BadRequestException("Book Already Exists");
        }
        book = dtoMapping.addBookDtoToModel(addBookDto);
        book.setBookId(generateSequence.generateSequence(Book.SEQUENCE_NAME));
        Book savedBook = bookRepository.save(book);
        if(StringUtils.isNotEmpty(savedBook.getBookName()))
            return "Book Added successfully";
        else
            return "Something went wrong";
    }

    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books){
            bookDtos.add(dtoMapping.modelToBookDto(book));
        }
        return bookDtos;
    }

    public BookDto getBookByName(String name) {
        Book book = bookRepository.findByBookName(name);
        BookDto bookDto = new BookDto();
        if(null == book)
            throw new BadRequestException("Book not found");
        else{
            bookDto = dtoMapping.modelToBookDto(book);
        }
        return bookDto;
    }

    public BookDto updateBook(AddBookDto addBookDto, String name) {
        BookDto bookDto = new BookDto();
        Book book = bookRepository.findByBookName(name);
        if(null == book){
            throw new BadRequestException("Book not found");
        }else {
            book.setBookName(addBookDto.getBookName());
            book.setBookPrice(addBookDto.getBookPrice());
            book.setBookDescription(addBookDto.getBookDescription());
            book.setBookAuthor(addBookDto.getBookAuthor());
            book.setCategory(addBookDto.getCategory());
            book.setBookQuantity(addBookDto.getBookQuantity());
            bookRepository.save(book);
            bookDto = dtoMapping.modelToBookDto(book);
        }
        return bookDto;
    }

    public String deleteBookByName( String name){

        Book book = bookRepository.findByBookName(name);
        if(null == book){
            throw new BadRequestException("Book not found");
        }else {
            bookRepository.delete(book);
        }

        return "Book deleted successfully";
    }

}
