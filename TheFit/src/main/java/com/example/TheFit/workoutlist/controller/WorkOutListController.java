package com.example.TheFit.workoutlist.controller;

import com.example.TheFit.workout.dto.WorkOutDto;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.dto.WorkOutListDto;
import com.example.TheFit.workoutlist.service.WorkOutListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkOutListController {
    private final WorkOutListService workOutListService;

    @Autowired
    public WorkOutListController(WorkOutListService workOutListService) {
        this.workOutListService = workOutListService;
    }
    @PostMapping("workout/list/create")
    public String create(@RequestBody WorkOutListDto workOutListDto){
        workOutListService.create(workOutListDto);
        return "workOutList create ok";
    }
    @DeleteMapping("workout/list/delete/{id}")
    public String delete(@PathVariable Long id) {
        workOutListService.delete(id);
        return "workOutList delete ok";
    }
}
