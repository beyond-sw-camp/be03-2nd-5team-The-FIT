package com.example.TheFit.workout.controller;

import com.example.TheFit.workout.dto.WorkOutReqDto;
import com.example.TheFit.workout.dto.WorkOutResDto;
import com.example.TheFit.workout.service.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String create(@Valid @RequestBody WorkOutReqDto workOutReqDto) {
        workOutService.create(workOutReqDto);
        return "workOut create ok";
    }

    @GetMapping("/list")
    public List<WorkOutResDto> findAll() {
        return workOutService.findAll();
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @RequestBody WorkOutReqDto workOutReqDto) {
        workOutService.update(id, workOutReqDto);
        return "workOut update ok";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        workOutService.delete(id);
        return "workOut delete ok";
    }
}