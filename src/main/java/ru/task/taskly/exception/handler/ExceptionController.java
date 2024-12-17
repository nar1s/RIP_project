package ru.task.taskly.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.task.taskly.exception.IncorrectPasswordException;
import ru.task.taskly.exception.RegistrationException;
import ru.task.taskly.model.dto.DealCreateDto;
import ru.task.taskly.model.dto.UserEnterDto;
import ru.task.taskly.model.dto.UserRegistrationDto;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public String userNotFound(EntityNotFoundException exception,
                               Model model){
        model.addAttribute("error", exception.getMessage());
        model.addAttribute("user", new UserEnterDto());
        return "enter";
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public String incorrectPassword(IncorrectPasswordException exception,
                                    Model model){
        model.addAttribute("error", exception.getMessage());
        model.addAttribute("user", new UserEnterDto());
        return "enter";
    }

    @ExceptionHandler(RegistrationException.class)
    public String registrationException(RegistrationException exception,
                                        Model model){
        model.addAttribute("error", exception.getMessage());
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @ExceptionHandler(DateTimeParseException.class)
    public String dateTimeParseException(DateTimeParseException exception,
                                         Model model){
        model.addAttribute("error", "Формат даты гггг-мм-дд\nФормат времени чч:мм:сс");
        model.addAttribute("task", new DealCreateDto());
        return "add_task";
    }
}
