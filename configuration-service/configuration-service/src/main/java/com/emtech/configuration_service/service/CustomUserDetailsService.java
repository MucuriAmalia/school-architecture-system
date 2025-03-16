package com.emtech.configuration_service.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public CustomUserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT username, password, role FROM users WHERE username = ?";

        List<UserDetails> users = jdbcTemplate.query(sql, new Object[]{username}, userRowMapper());

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return users.get(0); // Return the first result
    }

    private RowMapper<UserDetails> userRowMapper() {
        return (rs, rowNum) -> {
            String username = rs.getString("username");
            String password = rs.getString("password");
            String role = rs.getString("role");

            return User.builder()
                    .username(username)
                    .password(password)
                    .roles(role) // Assign role (ADMIN, TEACHER, STUDENT)
                    .build();
        };
    }
}
