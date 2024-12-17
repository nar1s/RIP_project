package ru.task.taskly.exception;

public class LoginUserException extends RuntimeException{
    public LoginUserException(String message) {
        super(message);
    }
}
