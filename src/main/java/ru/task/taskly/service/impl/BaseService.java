package ru.task.taskly.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.task.taskly.exception.JsonProcessingTasklyException;

@Service
public class BaseService {
    public String convertToJSON(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception){
            throw new JsonProcessingTasklyException("Не удалось конвертировать в JSON!");
        }
    }
}
