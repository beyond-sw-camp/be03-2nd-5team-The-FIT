package com.example.TheFit.diet.controller;

import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietDto;
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

    @PostMapping("/create")
    public String createDiet(@RequestBody DietDto dietDto) {
        dietService.create(dietDto);
        return "diet create ok";
    }
    @GetMapping("/{id}")
    public DietDto findById(@PathVariable Long id) {
        return dietService.findById(id);
    }
    @PatchMapping("/update/{id}")
    public String updateDiet(@PathVariable Long id, @RequestBody DietDto dietDto) {
        dietService.update(id, dietDto);
        return "diet update ok";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Diet diet = dietService.delete(id);
        return "diet delete Ok";
    }
}