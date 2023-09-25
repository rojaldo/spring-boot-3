package com.example.demo.trivial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrivialService {

    @Autowired
    TrivialRepository trivialRepository;

    public List<TrivialCardDto> getTrivialCards() {

        // get all cards from the database
        List<TrivialCardEntity> trivialCardEntities = trivialRepository.findAll();
        // convert them to DTOs
        List<TrivialCardDto> trivialCardDtos = new ArrayList<>();
        for (TrivialCardEntity trivialCardEntity : trivialCardEntities) {
            TrivialCardDto trivialCardDto = TrivialCardDto.builder()
                .category(trivialCardEntity.getCategory())
                .rightAnswer(trivialCardEntity.getRightAnswer())
                .wrongAnswers(trivialCardEntity.getWrongAnswers())
                .difficulty(trivialCardEntity.getDifficulty())
                .question(trivialCardEntity.getQuestion())
                .type(trivialCardEntity.getType())
                .build();
            trivialCardDtos.add(trivialCardDto);
        }
        return trivialCardDtos;

    }

    public TrivialCardDto addTrivialCard(TrivialCardDto trivialCardDto) {
        TrivialCardEntity trivialCardEntity = new TrivialCardEntity(trivialCardDto.getQuestion(),
                trivialCardDto.getCategory(), trivialCardDto.getRightAnswer(), trivialCardDto.getWrongAnswers(),
                trivialCardDto.getDifficulty(), trivialCardDto.getType());
        trivialRepository.save(trivialCardEntity);
        return trivialCardDto;
    }

    

}
