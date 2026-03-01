package com.schooldiary.repository;

import com.schooldiary.model.Role;
import com.schooldiary.model.User;

public class MockUserRepository implements UserRepository {

    @Override
    public User findByLogin(String login) {

        switch (login) {
            case "admin":
                return new User(1, "admin", "1234", Role.ADMIN);
            case "teacher":
                return new User(2, "teacher", "1234", Role.TEACHER);
            case "student":
                return new User(3, "student", "1234", Role.STUDENT);
            case "parent":
                return new User(4, "parent", "1234", Role.PARENT);
            default:
                return null;
        }
    }
}