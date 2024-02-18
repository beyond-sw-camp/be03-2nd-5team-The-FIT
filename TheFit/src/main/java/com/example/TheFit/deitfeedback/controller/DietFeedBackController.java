package com.example.TheFit.deitfeedback.controller;

import com.example.TheFit.deitfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.deitfeedback.dto.DietFeedBackResDto;
import com.example.TheFit.deitfeedback.service.DietFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("diet/feedback")
public class DietFeedBackController {

    private final DietFeedBackService dietFeedBackService;

    @Autowired
    public DietFeedBackController(DietFeedBackService dietFeedBackService) {
        this.dietFeedBackService = dietFeedBackService;
    }

    @PostMapping("/create")
    public String create(@Valid @RequestBody DietFeedBackReqDto dietFeedBackReqDto) {
        dietFeedBackService.create(dietFeedBackReqDto);
        return "DietFeedBack create ok";
    }
    @GetMapping("/list")
    public List<DietFeedBackResDto> dietFeedBackResDtos(){
        return dietFeedBackService.findAll();
    }
    @PatchMapping("update/{id}")
    public String update(@PathVariable Long id, @Valid @RequestBody DietFeedBackReqDto dietFeedBackReqDto) {
        dietFeedBackService.update(id, dietFeedBackReqDto);
        return "DietFeedBack update Ok";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        dietFeedBackService.delete(id);
        return "DietFeedBack delete ok";
    }

}