package com.example.demo.trivial;

import java.util.List;

import com.example.demo.user.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrivialCardEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    
    private String question;
    private String category;
    private String rightAnswer;
    private List<String> wrongAnswers;
    private String difficulty;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TrivialCardEntity(String question, String category, String rightAnswer, List<String> wrongAnswers,
            String difficulty, String type) {
        this.question = question;
        this.category = category;
        this.rightAnswer = rightAnswer;
        this.wrongAnswers = wrongAnswers;
        this.difficulty = difficulty;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setWrongAnswers(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setType(String type) {
        this.type = type;
    }

}
