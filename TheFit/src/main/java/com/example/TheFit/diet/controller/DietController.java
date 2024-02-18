package com.example.TheFit.diet.controller;

import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.diet.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/diet")
public class DietController {

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @PostMapping("/create")
    public String createDiet(@Valid @RequestBody DietReqDto dietReqDto) {
        dietService.create(dietReqDto);
        return "diet create ok";
    }
    @GetMapping("/{id}")
    public DietResDto findById(@PathVariable Long id) {
        return dietService.findById(id);
    }
    @PatchMapping("/update/{id}")
    public String updateDiet(@PathVariable Long id, @Valid @RequestBody DietReqDto dietReqDto) {
        dietService.update(id, dietReqDto);
        return "diet update ok";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        dietService.delete(id);
        return "diet delete Ok";
    }
}