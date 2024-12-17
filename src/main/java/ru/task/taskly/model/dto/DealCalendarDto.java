package ru.task.taskly.model.dto;

public class DealCalendarDto {
    private Integer id;
    private String name;
    private String description;
    private Integer executionDays;

    public DealCalendarDto(Integer id, String name, String description, Integer executionDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.executionDays = executionDays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExecutionDays() {
        return executionDays;
    }

    public void setExecutionDays(Integer executionDays) {
        this.executionDays = executionDays;
    }
}
