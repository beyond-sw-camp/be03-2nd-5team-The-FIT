package com.example.TheFit.diet.controller;

import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietCreateDto;
import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.diet.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diet")
public class DietController {

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @PostMapping("/diet/create")
    public String createDiet(@RequestBody DietCreateDto dietCreateDto) {
        dietService.create(dietCreateDto);
        return "diet create ok";
    }
    @GetMapping("/diets/{id}")
    public DietResDto findById(@PathVariable Long id) {
        return dietService.findById(id);
    }
    @PatchMapping("/diet/update/{id}")
    public String updateDiet(@PathVariable Long id, @RequestBody DietReqDto dietReqDto) {
        dietService.update(id, dietReqDto);
        return "diet update ok";
    }
    @DeleteMapping("/diet/delete/{id}")
    public String delete(@PathVariable Long id){
        Diet diet = dietService.delete(id);
        return "diet delete Ok";
    }
}