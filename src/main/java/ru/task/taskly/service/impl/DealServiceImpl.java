package ru.task.taskly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.taskly.model.Deal;
import ru.task.taskly.model.Status;
import ru.task.taskly.model.User;
import ru.task.taskly.model.dto.DealCalendarDto;
import ru.task.taskly.model.dto.DealCreateDto;
import ru.task.taskly.model.dto.DealUpdateDto;
import ru.task.taskly.repository.DealRepository;
import ru.task.taskly.repository.UserRepository;
import ru.task.taskly.service.DealService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private DealRepository dealRepository;
    private UserRepository userRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository, UserRepository userRepository) {
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
    }

    public HashMap<String, List<DealCalendarDto>> getCalendarDeal(List<Deal> deals){
        HashMap<String, List<DealCalendarDto>> calendarDeal = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
                .withZone(ZoneId.systemDefault());
        for(Deal deal : deals){
            String dateString = formatter.format(deal.getDateEnd());
            if (!calendarDeal.containsKey(dateString)) {
                calendarDeal.put(dateString, new ArrayList<>());
            }
            calendarDeal.get(dateString).add(convertToDto(deal));
        }
        return calendarDeal;
    }
    private DealCalendarDto convertToDto(Deal deal) {
        long daysDifference = ChronoUnit.DAYS.between(deal.getDateStart(), deal.getDateEnd());
        Integer daysDiffAsInteger = Math.toIntExact(daysDifference);
        return new DealCalendarDto(deal.getId(), deal.getName(), deal.getDescription(), daysDiffAsInteger);
    }

    @Override
    public Deal addDeal(User user, DealCreateDto dealCreateDto){
        final String dealName = dealCreateDto.getName();
        final String dealDescription = dealCreateDto.getDescription();
        final String date = dealCreateDto.getDate();
        final String time = dealCreateDto.getTime();
        final String dateTime = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        Instant dateEnd = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Instant dateStart = Instant.now();
        Deal deal = new Deal(dealName, dealDescription, Status.IN_PROCESS, dateStart, dateEnd, user);
        return dealRepository.save(deal);
    }

    @Override
    public String getTodayDate(){
        Instant today = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
                .withZone(ZoneId.systemDefault());
        return formatter.format(today);
    }

    @Override
    public String getTomorrowDate(){
        Instant today = Instant.now();
        Instant tomorrow = today.plus(1, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
                .withZone(ZoneId.systemDefault());
        return formatter.format(tomorrow);
    }

    @Override
    public Deal updateDeal(DealUpdateDto dealUpdateDto){
        Deal deal = dealRepository.findById(dealUpdateDto.getId())
                .orElseThrow();
        deal.setDescription(dealUpdateDto.getDescription());
        return dealRepository.save(deal);
    }

    @Override
    public void deleteDeal(Integer dealId){
        dealRepository.deleteById(dealId);
    }

    @Override
    public HashMap<String, List<DealCalendarDto>> getDeal(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow();
        return getCalendarDeal(user.getDeals());
    }
}
