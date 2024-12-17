package ru.task.taskly.service;

import ru.task.taskly.model.Deal;
import ru.task.taskly.model.User;
import ru.task.taskly.model.dto.DealCalendarDto;
import ru.task.taskly.model.dto.DealCreateDto;
import ru.task.taskly.model.dto.DealUpdateDto;

import java.util.HashMap;
import java.util.List;

public interface DealService {
    HashMap<String, List<DealCalendarDto>> getCalendarDeal(List<Deal> deals);
    Deal addDeal(User user, DealCreateDto dealCreateDto);
    String getTodayDate();
    String getTomorrowDate();
    Deal updateDeal(DealUpdateDto dealUpdateDto);
    void deleteDeal(Integer dealId);
    HashMap<String, List<DealCalendarDto>> getDeal(Integer userId);
}
