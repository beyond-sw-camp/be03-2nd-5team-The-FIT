package com.example.TheFit.workoutlist.controller;

import com.example.TheFit.workoutlist.dto.WorkOutListReqDto;
import com.example.TheFit.workoutlist.service.WorkOutListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WorkOutListController {
    private final WorkOutListService workOutListService;

    @Autowired
    public WorkOutListController(WorkOutListService workOutListService) {
        this.workOutListService = workOutListService;
    }

    @PostMapping("workout/list/create")
    public String create(@Valid @RequestBody WorkOutListReqDto workOutListReqDto){
        workOutListService.create(workOutListReqDto);
        return "WorkOutList create ok";
    }

    @DeleteMapping("workout/list/delete/{id}")
    public String delete(@PathVariable Long id) {
        workOutListService.delete(id);
        return "WorkOutList delete ok";
    }
}