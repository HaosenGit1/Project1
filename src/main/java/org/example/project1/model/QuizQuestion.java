package org.example.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestion {
    private int qqId;
    private int quizId;
    private int questionId;
    private Integer userChoiceId;


    private Question question;
    private List<Choice> choices;

    public boolean isCorrect() {
        if (choices == null || userChoiceId == null) {
            return false;
        }
        return choices.stream()
                .anyMatch(c -> c.getChoiceId() == userChoiceId && c.isCorrect());
    }

}
