package ru.task.taskly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.taskly.exception.EmptyUpdateFieldException;
import ru.task.taskly.exception.LoginUserException;
import ru.task.taskly.model.User;
import ru.task.taskly.model.dto.UserUpdateDto;
import ru.task.taskly.repository.UserRepository;
import ru.task.taskly.service.ProfileService;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUser(User user, UserUpdateDto userUpdateDto){
        if(userUpdateDto.getLogin().isEmpty() && userUpdateDto.getPassword().isEmpty()) {
            throw new EmptyUpdateFieldException("Хотя бы одно поле должно быть заполнено.");
        }
        if(!userUpdateDto.getLogin().isEmpty()){
            Optional<User> findUser = userRepository.findByLogin(userUpdateDto.getLogin());
            if (findUser.isPresent()) {
                throw new LoginUserException("Пользоваель с таким логином существует.");
            }
            user.setLogin(userUpdateDto.getLogin());
        }
        if(!userUpdateDto.getPassword().isEmpty()){
            user.setPassword(userUpdateDto.getPassword());
        }
        return userRepository.save(user);
    }
}
