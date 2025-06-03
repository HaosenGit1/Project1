package org.example.project1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private boolean isActive;
    private boolean isAdmin;
}
