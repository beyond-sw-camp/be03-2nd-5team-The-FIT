package com.example.TheFit.workoutlist.controller;

import com.example.TheFit.workoutlist.dto.WorkOutListReqDto;
import com.example.TheFit.workoutlist.dto.WorkOutListResDto;
import com.example.TheFit.workoutlist.service.WorkOutListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("workout_list")
public class WorkOutListController {
    private final WorkOutListService workOutListService;

    @Autowired
    public WorkOutListController(WorkOutListService workOutListService) {
        this.workOutListService = workOutListService;
    }

    @PostMapping("/create")
    public String create(@Valid @RequestBody WorkOutListReqDto workOutListReqDto){
        workOutListService.create(workOutListReqDto);
        return "WorkOutList create ok";
    }

    @GetMapping("/list")
    public List<WorkOutListResDto> create(){
        return workOutListService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        workOutListService.delete(id);
        return "WorkOutList delete ok";
    }
}