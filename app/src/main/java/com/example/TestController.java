package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Аннотация, указывающая, что класс является контроллером в Spring
@RestController
@RequestMapping("/api")  // Базовый путь для всех методов в этом контроллере
public class TestController {

    // Аннотация для GET-запроса на путь "/api/hello"
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";  // Возвращает строку в ответ на запрос
    }
}