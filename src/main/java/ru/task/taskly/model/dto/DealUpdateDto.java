package ru.task.taskly.model.dto;

public class DealUpdateDto {
    private Integer id;
    private String description;

    public DealUpdateDto() {

    }

    public DealUpdateDto(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
