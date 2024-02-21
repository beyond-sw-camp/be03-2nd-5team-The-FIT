package com.example.TheFit.feedback.dietfeedback.controller;

import com.example.TheFit.common.TheFitResponse;
import com.example.TheFit.feedback.dietfeedback.domain.DietFeedBack;
import com.example.TheFit.feedback.dietfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.feedback.dietfeedback.dto.DietFeedBackResDto;
import com.example.TheFit.feedback.dietfeedback.service.DietFeedBackService;
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
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success create",dietFeedBack.getId()),HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<TheFitResponse> dietFeedBackResDtos(){
        List<DietFeedBackResDto> dietFeedBackResDtos = dietFeedBackService.findAll();
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success create",dietFeedBackResDtos),HttpStatus.CREATED);
    }
    @GetMapping("/my")
    public ResponseEntity<TheFitResponse> myFeedback(){
        DietFeedBackResDto dietFeedBackResDto = dietFeedBackService.findFeedBack();
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success create",dietFeedBackResDto),HttpStatus.CREATED);
    }
    @PatchMapping("update/{id}")
    public ResponseEntity<TheFitResponse> update(@PathVariable Long id, @Valid @RequestBody DietFeedBackReqDto dietFeedBackReqDto) {
        DietFeedBack dietFeedBack = dietFeedBackService.update(id, dietFeedBackReqDto);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success update",dietFeedBack.getId()),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TheFitResponse> delete(@PathVariable Long id) {
        dietFeedBackService.delete(id);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success delete"),HttpStatus.OK);
    }
}