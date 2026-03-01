package com.schooldiary.repository;

import com.schooldiary.model.User;

public interface UserRepository {
    User findByLogin(String login);
}