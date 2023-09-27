package com.example.demo.books;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class BookDto {
    
    @NonNull
    @Pattern(regexp = "^[0-9]{3}-[0-9]{10}$")
    private String isbn;

    @NonNull
    @Length(min = 1, max = 100)
    private String title;

    @NonNull
    @Length(min = 1, max = 100)
    private String author;

    // long text
    @Length(max = 10000)
    private String description;
    
}
