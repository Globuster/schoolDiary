package com.schooldiary.repository;

import com.schooldiary.config.DatabaseConnection;
import com.schooldiary.model.Role;
import com.schooldiary.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUserRepository implements UserRepository {

    @Override
    public User findByLogin(String login) {

        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE login = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}