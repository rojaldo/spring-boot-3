package com.example.demo.trivial;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/trivial")
public class TrivialRestController {

    @Autowired
    TrivialService trivialService;

    @GetMapping("/cards")
    public ResponseEntity<?> trivial(
            @RequestParam(name = "difficulty", required = false, defaultValue = "any") String difficulty,
            @RequestParam(name = "type", required = false, defaultValue = "any") String type,
            @RequestParam(name = "category", required = false, defaultValue = "-1") int category
            ) {
        // get all cards from the database
        if(this.trivialService.difficultyIsValid(difficulty) && this.trivialService.typeIsValid(type) && this.trivialService.categoryIsValid(category)){
            Iterable<TrivialCardDto> trivialCardDtos = trivialService.getTrivialCards(difficulty, type, category);
            return ResponseEntity.status(200).body(trivialCardDtos);
        }else{
            return ResponseEntity.status(400).body(new HashMap<String, String>() {
                {
                    put("message", "wrong parameters");
                    put("difficulty", difficulty);
                    put("type", type);
                    put("category", Integer.toString(category));
                }
            });
        }
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

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<?> deleteTrivialCard(
            @PathVariable("id") Long id) {
        try {
            TrivialCardDto result = this.trivialService.deleteTrivialCard(id);
            return ResponseEntity.status(200).body(result);

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
