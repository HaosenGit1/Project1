package org.example.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
    private int choiceId;
    private int questionId;
    private String description;
    private boolean isCorrect;
}

