package ru.task.taskly.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.taskly.exception.IncorrectPasswordException;
import ru.task.taskly.exception.RegistrationException;
import ru.task.taskly.model.User;
import ru.task.taskly.model.dto.UserEnterDto;
import ru.task.taskly.model.dto.UserRegistrationDto;
import ru.task.taskly.repository.AuthorizationRepository;
import ru.task.taskly.service.AuthorizationService;

import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private AuthorizationRepository authorizationRepository;

    @Autowired
    public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public User authorize(UserEnterDto userEnterDto) {
        Optional<User> optional = authorizationRepository.findUserByUsername(userEnterDto.getLogin());
        User user = optional.orElseThrow(
                () -> new EntityNotFoundException("Пользователь с именем " + userEnterDto.getLogin() + " не найден")
        );
        if (!user.getPassword().equals(userEnterDto.getPassword())) {
            throw new IncorrectPasswordException("Пароли должны совпадать");
        }
        return user;
    }

    @Override
    public User registration(UserRegistrationDto userRegistrationDto) {
        final String password = userRegistrationDto.getPassword();
        final String acceptPassword = userRegistrationDto.getAcceptPassword();
        final String username = userRegistrationDto.getUsername();
        final String email = userRegistrationDto.getEmail();

        if (!password.equals(acceptPassword)) {
            throw new RegistrationException("Пароли должны совпадать.");
        }

        Boolean isUsernameUnique = authorizationRepository.isUsernameUnique(username);

        if (isUsernameUnique == null || !isUsernameUnique) {
            throw new RegistrationException("Такое имя пользователя уже существует.");
        }

        Boolean isEmailUnique = authorizationRepository.isEmailUnique(email);

        if (isEmailUnique == null || !isEmailUnique) {
            throw new RegistrationException("Почта уже принадлежит пользователю.");
        }

        User user = authorizationRepository.saveUser(username, email, password);
        if (user == null) {
            throw new RegistrationException("Регистрация не прошла. Повторите попытку.");
        }

        return user;
    }
}
