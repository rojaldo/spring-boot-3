package com.example.demo.books;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRespository extends JpaRepository<BookEntity, Long> {

    BookEntity findById(long id);
    BookEntity findByIsbn(String isbn);
    BookEntity findByTitle(String title);
    BookEntity findByAuthor(String author);
    BookEntity findByAuthorAndTitle(String author, String title);

}
