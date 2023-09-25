package com.example.demo.trivial;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/trivial")
public class TrivialController {

    @Autowired
    TrivialService trivialService;

    @RequestMapping("/cards")
    public ResponseEntity<Iterable<TrivialCardDto>> trivial() {
        // get all cards from the database
        Iterable<TrivialCardDto> trivialCardDtos = trivialService.getTrivialCards();
        return ResponseEntity.status(200).body(trivialCardDtos);
    }

    @PostMapping("/cards")
    public ResponseEntity<TrivialCardDto> addTrivialCard(@RequestBody @Valid TrivialCardDto trivialCardDto) {
        TrivialCardDto result = this.trivialService.addTrivialCard(trivialCardDto);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<?> updateTrivialCard(
            @PathVariable("id") Long id,
            @RequestBody @Valid TrivialCardDto trivialCardDto) {
        try {
            TrivialCardDto result = this.trivialService.updateTrivialCard(trivialCardDto, id);
            return ResponseEntity.status(202).body(result);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(new HashMap<String, String>() {
                {
                    put("message", "wrong id");
                    put("id", id.toString());
                }
            });
        }

    }
}
