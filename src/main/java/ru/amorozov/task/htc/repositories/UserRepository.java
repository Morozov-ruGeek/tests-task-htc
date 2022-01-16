package ru.amorozov.task.htc.repositories;

import ru.amorozov.task.htc.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    boolean isExist(Long id);
}
