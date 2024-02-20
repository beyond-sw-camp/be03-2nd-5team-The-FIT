package com.example.TheFit.dietfeedback.controller;

import com.example.TheFit.common.TheFitResponse;
import com.example.TheFit.dietfeedback.domain.DietFeedBack;
import com.example.TheFit.dietfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.dietfeedback.dto.DietFeedBackResDto;
import com.example.TheFit.dietfeedback.service.DietFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TheFitResponse> create(@Valid @RequestBody DietFeedBackReqDto dietFeedBackReqDto) {
        DietFeedBack dietFeedBack = dietFeedBackService.create(dietFeedBackReqDto);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success create",dietFeedBack),HttpStatus.CREATED);
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