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

    public void updateUser(String nickname, long id) {
        String updateSql = "UPDATE user SET nickname = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, nickname, id);
    }

    public boolean userNotExist(long id) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void deleteUser(String nickname) {
        String deleteSql = "DELETE FROM user WHERE nickname = ?";
        jdbcTemplate.update(deleteSql, nickname);
    }

    public boolean userNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE nickname = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }
}
