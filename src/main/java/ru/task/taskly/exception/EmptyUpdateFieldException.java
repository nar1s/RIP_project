package ru.task.taskly.exception;

public class EmptyUpdateFieldException extends RuntimeException {
    public EmptyUpdateFieldException(String message) {
        super(message);
    }
}
