package ru.task.taskly.service;

import ru.task.taskly.model.User;
import ru.task.taskly.model.dto.UserEnterDto;
import ru.task.taskly.model.dto.UserRegistrationDto;

import java.util.Optional;

public interface AuthorizationService {
    User authorize(UserEnterDto userEnterDto);
    User registration(UserRegistrationDto userRegistrationDto);
}
