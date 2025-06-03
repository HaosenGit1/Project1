package org.example.project1.dao;


import org.example.project1.model.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import java.util.List;

@Repository
public class ContactDao {

    private final JdbcTemplate jdbcTemplate;

    public ContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> findAll() {
        return jdbcTemplate.query("SELECT * FROM Contact ORDER BY time DESC",
                (rs, rowNum) -> new Contact(rs.getInt("contact_id"), rs.getString("subject"),
                        rs.getString("message"), rs.getString("email"),
                        rs.getTimestamp("time"))
        );
    }

    public int create(Contact contact) {
        return jdbcTemplate.update("INSERT INTO Contact (subject, message, email) VALUES (?, ?, ?)",
                contact.getSubject(), contact.getMessage(), contact.getEmail());
    }
}
