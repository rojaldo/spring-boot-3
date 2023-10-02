package com.example.demo.trivial;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;

@Service
public class TrivialService {

    String[] categories = {"General Knowledge", "Entertainment: Books", "Entertainment: Film", "Entertainment: Music", "Entertainment: Musicals & Theatres", "Entertainment: Television", "Entertainment: Video Games", "Entertainment: Board Games", "Science & Nature", "Science: Computers", "Science: Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Art", "Celebrities", "Animals", "Vehicles", "Entertainment: Comics", "Science: Gadgets", "Entertainment: Japanese Anime & Manga", "Entertainment: Cartoon & Animations"};

    @Autowired
    TrivialRepository trivialRepository;

    @Autowired
    private RestTemplate resTemplate;

    public List<TrivialCardDto> getTrivialCards(String difficulty, String type, int category) {

        // get all cards from the database
        List<TrivialCardEntity> trivialCardEntities = new ArrayList<TrivialCardEntity>();
        if (difficulty.equals("any") && type.equals("any") && category == -1) {
            trivialCardEntities = trivialRepository.findAll();
        } else if (!difficulty.equals("any") && type.equals("any") && category == -1) {
            trivialCardEntities = trivialRepository.findByDifficulty(difficulty);
        } else if (difficulty.equals("any") && !type.equals("any") && category == -1) {
            trivialCardEntities = trivialRepository.findByType(type);
        } else if (difficulty.equals("any") && type.equals("any") && category != -1) {
            trivialCardEntities = trivialRepository.findByCategory(categories[category]);
        }else if (!difficulty.equals("any") && !type.equals("any") && category == -1) {
            trivialCardEntities = trivialRepository.findByDifficultyAndType(difficulty, type);
        }else if (!difficulty.equals("any") && type.equals("any") && category != -1) {
            trivialCardEntities = trivialRepository.findByCategoryAndDifficulty(categories[category], difficulty);
        }else if (difficulty.equals("any") && !type.equals("any") && category != -1) {
            trivialCardEntities = trivialRepository.findByCategoryAndType(categories[category], type);
        }else if (!difficulty.equals("any") && !type.equals("any") && category != -1) {
            trivialCardEntities = trivialRepository.findByCategoryAndDifficultyAndType(categories[category], difficulty, type);
        }
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

    @Transactional
    public TrivialCardDto addTrivialCard(TrivialCardDto trivialCardDto) {
        TrivialCardEntity trivialCardEntity = new TrivialCardEntity(trivialCardDto.getQuestion(),
                trivialCardDto.getCategory(), trivialCardDto.getRightAnswer(), trivialCardDto.getWrongAnswers(),
                trivialCardDto.getDifficulty(), trivialCardDto.getType());
        trivialRepository.save(trivialCardEntity);
        return trivialCardDto;
    }

    @Transactional
    public TrivialCardDto updateTrivialCard(TrivialCardDto trivialCardDto, long id) throws NoSuchElementException {
        // find the card in the database
        TrivialCardEntity trivialCardEntity = null;
        trivialCardEntity = trivialRepository.findById(id).get();

        // if it exists, update it
        if (trivialCardEntity != null) {
            trivialCardEntity.setCategory(trivialCardDto.getCategory());
            trivialCardEntity.setDifficulty(trivialCardDto.getDifficulty());
            trivialCardEntity.setQuestion(trivialCardDto.getQuestion());
            trivialCardEntity.setRightAnswer(trivialCardDto.getRightAnswer());
            trivialCardEntity.setType(trivialCardDto.getType());
            trivialCardEntity.setWrongAnswers(trivialCardDto.getWrongAnswers());
            trivialRepository.save(trivialCardEntity);
            return trivialCardDto;
        }
        // if it doesn't exist, return null
        else {
            return null;
        }

    }

    @Transactional
    public TrivialCardDto deleteTrivialCard(long id) throws NoSuchElementException {
        // find the card in the database
        TrivialCardEntity trivialCardEntity = null;
        trivialCardEntity = trivialRepository.findById(id).get();

        // if it exists, delete it
        if (trivialCardEntity != null) {
            trivialRepository.delete(trivialCardEntity);
            return TrivialCardDto.builder()
                    .category(trivialCardEntity.getCategory())
                    .rightAnswer(trivialCardEntity.getRightAnswer())
                    .wrongAnswers(trivialCardEntity.getWrongAnswers())
                    .difficulty(trivialCardEntity.getDifficulty())
                    .question(trivialCardEntity.getQuestion())
                    .type(trivialCardEntity.getType())
                    .build();
        }
        // if it doesn't exist, return null
        else {
            return null;
        }

    }

    @Transactional
    public void getCardsFromApi(String url) {
        // do a get request to the url
        List<TrivialCardEntity> cardEntities = new ArrayList<TrivialCardEntity>();
        String response = resTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);

        // Obtén el array dentro de la propiedad "results"
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");
        // Convierte el array de resultados a una lista de TrivialCardDto
        List<OTBCardDto> cards = new ArrayList<OTBCardDto>();

        for (int i = 0; i < resultsArray.size(); i++) {
            JsonObject cardJson = resultsArray.get(i).getAsJsonObject();
            OTBCardDto card = gson.fromJson(cardJson, OTBCardDto.class);
            cards.add(card);
        }

        // convert the list of TrivialCardDto to a list of TrivialCardEntity

        for (OTBCardDto cardDto : cards) {
            TrivialCardEntity cardEntity = TrivialCardEntity.builder()
                    .category(cardDto.getCategory())
                    .difficulty(cardDto.getDifficulty())
                    .question(cardDto.getQuestion())
                    .rightAnswer(cardDto.getCorrect_answer())
                    .type(cardDto.getType())
                    .wrongAnswers(cardDto.getIncorrect_answers())
                    .build();
            cardEntities.add(cardEntity);
        }

        trivialRepository.saveAll(cardEntities);
    }

    boolean difficultyIsValid(String difficulty) {
        return difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")
                || difficulty.equals("any");
    }

    boolean typeIsValid(String type) {
        return type.equals("multiple") || type.equals("boolean") || type.equals("any");
    }

    boolean categoryIsValid(int category) {
        return category >= -1 && category <= this.categories.length - 1;
    }

}
