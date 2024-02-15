package com.example.TheFit.workout.controller;

import com.example.TheFit.career.dto.CareerDto;
import com.example.TheFit.workout.dto.WorkOutDto;
import com.example.TheFit.workout.service.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkOutController {
    private final WorkOutService workOutService;
    @Autowired
    public WorkOutController(WorkOutService workOutService) {
        this.workOutService = workOutService;
    }

    @PostMapping("/create")
    public String create(@RequestBody WorkOutDto workOutDto){
        workOutService.create(workOutDto);
        return "workOut create ok";
    }
    @GetMapping("/list")
    public List<WorkOutDto> WorkOut(){
        return workOutService.findAll();
    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody WorkOutDto workOutDto){
        workOutService.update(id, workOutDto);
        return "workOut update ok";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        workOutService.delete(id);
        return "workOut delete ok";
    }
}
