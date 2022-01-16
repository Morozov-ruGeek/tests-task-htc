package ru.amorozov.task.htc.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amorozov.task.htc.repositories.UserRepository;
import ru.amorozov.task.htc.services.UserService;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String findUserById(Long userId) {
        if (userRepository.isExist(userId)){
            return userRepository.findById(userId).toString();
        } else {
            log.debug("User not found userId - {}", userId);
            return "User not found";
        }
    }
}
