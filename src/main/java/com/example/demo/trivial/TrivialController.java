package com.example.demo.trivial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Iterable<TrivialCardDto>> trivial(){
        // get all cards from the database
        Iterable<TrivialCardDto> trivialCardDtos = trivialService.getTrivialCards();
        return ResponseEntity.status(200).body(trivialCardDtos);
    }

    @PostMapping("/cards")
    public ResponseEntity<TrivialCardDto> addTrivialCard(@RequestBody @Valid TrivialCardDto trivialCardDto){
        TrivialCardDto result = this.trivialService.addTrivialCard(trivialCardDto);
        return ResponseEntity.status(201).body(result);
    }
}
