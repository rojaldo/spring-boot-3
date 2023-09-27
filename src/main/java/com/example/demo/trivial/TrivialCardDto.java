package com.example.demo.trivial;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// this class maps a json as this:

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrivialCardDto {

    @NotBlank
    private String question;

    @NotBlank
    private String category;

    @NotBlank
    @JsonProperty("correct_answer")
    private String rightAnswer;

    @JsonProperty("incorrect_answers")
    @Size(min = 1, max = 3)
    private List<String> wrongAnswers;

    @NotBlank
    private String difficulty;

    @NotBlank
    private String type;

    @JsonProperty("user_id")
    private long userId;

    

}
