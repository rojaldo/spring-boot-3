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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class BooksRestController {

    @Autowired
    BooksService booksService;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
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
