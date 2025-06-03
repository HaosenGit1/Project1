package org.example.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private int contactId;
    private String subject;
    private String message;
    private String email;
    private Timestamp time;
}
