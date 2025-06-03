package org.example.project1;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AdminPassword {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1234"));

    }
}
