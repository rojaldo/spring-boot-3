package com.example.demo.trivial;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OTBCardDto {

    @NotBlank
    private String question;

    @NotBlank
    private String category;

    @NotBlank
    private String correct_answer;

    @Size(min = 1, max = 3)
    private List<String> incorrect_answers;

    @NotBlank
    private String difficulty;

    @NotBlank
    private String type;
    
}
