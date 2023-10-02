package com.example.demo.books;


import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    BooksRespository booksRespository;

    public BookDto createBook(BookDto book) {
        BookEntity bookEntity = BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .build();
        this.booksRespository.save(bookEntity);
        return book;
    }

    public BookDto getBookById(long id) {
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return null;
        }
        return BookDto.builder()
                .isbn(be.getIsbn())
                .title(be.getTitle())
                .author(be.getAuthor())
                .description(be.getDescription())
                .build();
    }

    public BookEntity getBookEntityById(long id) {
        return this.booksRespository.findById(id);
    }

    public Iterable<BookDto> getAllBooks() {
        Iterable<BookEntity> bookEntities = this.booksRespository.findAll();
        return this.convertBookEntitiesToBookDtos(bookEntities);
    }

    public BookDto deleteBookById (long id) {
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return null;
        }else {
            this.booksRespository.deleteById(id);
            return BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .build();
        }
    }

    public BookDto modifyBook (BookDto book, long id){
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return null;
        }else {
            be.setIsbn(book.getIsbn());
            be.setTitle(book.getTitle());
            be.setAuthor(book.getAuthor());
            be.setDescription(book.getDescription());
            this.booksRespository.save(be);
            return BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .build();
        }
    }

    private Iterable<BookDto> convertBookEntitiesToBookDtos(Iterable<BookEntity> bookEntities) {
        return StreamSupport.stream(bookEntities.spliterator(), false)
                .map(be -> BookDto.builder()
                        .isbn(be.getIsbn())
                        .title(be.getTitle())
                        .author(be.getAuthor())
                        .description(be.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    
}
