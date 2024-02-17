package com.example.TheFit.workoutfeedback.controller;

import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackReqDto;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackResDto;
import com.example.TheFit.workoutfeedback.service.WorkOutFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("workout/feedback")
public class WorkOutFeedBackController {

    private final WorkOutFeedBackService workOutFeedBackService;

    @Autowired
    public WorkOutFeedBackController(WorkOutFeedBackService workOutFeedBackService) {
        this.workOutFeedBackService = workOutFeedBackService;
    }

    @PostMapping("/create")
    public String create(@RequestBody WorkOutFeedBackReqDto workOutFeedBackReqDto) {
        workOutFeedBackService.create(workOutFeedBackReqDto);
        return "WorkOutFeedBack create ok";
    }
    @GetMapping("/list")
    public List<WorkOutFeedBackResDto> workOutFeedBacks(){
        return workOutFeedBackService.findAll();
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody WorkOutFeedBackReqDto workOutFeedBackReqDto) {
        workOutFeedBackService.update(id, workOutFeedBackReqDto);
        return "WorkOutFeedBack update Ok";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        workOutFeedBackService.delete(id);
        return "workOutList delete ok";
    }
}