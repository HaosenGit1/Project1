package org.example.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private int questionId;
    private int categoryId;
    private String description;
    private boolean isActive;
}
