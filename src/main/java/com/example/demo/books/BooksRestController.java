package com.example.demo.books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BooksRestController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books")
    public ResponseEntity<Iterable<BookDto>> getBooks() {
        return ResponseEntity.ok().body(this.booksService.getAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@RequestBody @Validated BookDto book) {
        return ResponseEntity.ok().body(this.booksService.createBook(book));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> modifyBook(@RequestBody @Validated BookDto book, @PathVariable long id) {
        return ResponseEntity.ok().body(this.booksService.modifyBook(book, id));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable long id) {
        BookDto book = this.booksService.deleteBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(book);
        }
    }
    
}
