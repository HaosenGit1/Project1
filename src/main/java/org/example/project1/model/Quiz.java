package org.example.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    private int quizId;
    private int userId;
    private int categoryId;
    private String name;
    private Timestamp timeStart;
    private Timestamp timeEnd;
}
