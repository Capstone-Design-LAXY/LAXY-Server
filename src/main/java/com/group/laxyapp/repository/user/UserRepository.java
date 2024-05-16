package com.group.laxyapp.repository.user;

import com.group.laxyapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void registUser(String nickname) {
        String sql = "INSERT INTO user (nickname) VALUES (?)";
        jdbcTemplate.update(sql, nickname);
    }

    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String nickname = rs.getString("nickname");
            return new UserResponse(id, nickname);
        });
    }
}
