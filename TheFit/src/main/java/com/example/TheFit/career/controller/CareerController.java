package com.example.TheFit.career.controller;

import com.example.TheFit.career.dto.CareerDto;
import com.example.TheFit.career.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {
    private final CareerService careerService;
    @Autowired
    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping("/create")
    public String create(@RequestBody CareerDto careerDto){
        careerService.create(careerDto);
        return "career create ok";
    }
    @GetMapping("/list")
    public List<CareerDto> Career(){
        return careerService.findAll();
    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody CareerDto careerDto){
        careerService.update(id, careerDto);
        return "career update ok";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        careerService.delete(id);
        return "career delete ok";
    }
}
