package ru.task.taskly.exception;

public class RegistrationException extends RuntimeException{
    public RegistrationException(String message) {
        super(message);
    }
}
