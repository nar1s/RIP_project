package ru.task.taskly.service;

import ru.task.taskly.model.User;
import ru.task.taskly.model.dto.UserUpdateDto;

public interface ProfileService {
    User updateUser(User user, UserUpdateDto userUpdateDto);
}
