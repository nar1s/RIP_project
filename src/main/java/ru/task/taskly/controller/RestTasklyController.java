package ru.task.taskly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task.taskly.model.Deal;
import ru.task.taskly.model.dto.DealCalendarDto;
import ru.task.taskly.model.dto.DealUpdateDto;
import ru.task.taskly.service.DealService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestTasklyController {

    private DealService dealService;

    public RestTasklyController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/update/task")
    public ResponseEntity<Deal> updateDeal(@RequestBody DealUpdateDto dealUpdateDto){
        Deal deal = dealService.updateDeal(dealUpdateDto);
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }

    @DeleteMapping("/delete/task/{id}")
    public ResponseEntity<Deal> deleteDeal(@PathVariable("id") Integer dealId){
        dealService.deleteDeal(dealId);
        System.out.println("Удаление прошло успешно");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<HashMap<String, List<DealCalendarDto>>> getDeal(@PathVariable("id") Integer userId){
        HashMap<String, List<DealCalendarDto>> deals = dealService.getDeal(userId);
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }
}
