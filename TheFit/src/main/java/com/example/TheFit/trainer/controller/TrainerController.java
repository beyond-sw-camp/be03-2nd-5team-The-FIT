package com.example.TheFit.trainer.controller;

import com.example.TheFit.trainer.dto.TrainerCreateDto;
import com.example.TheFit.trainer.dto.TrainerReqDto;
import com.example.TheFit.trainer.dto.TrainerResDto;
import com.example.TheFit.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/trainer/create")
    public String create(@RequestBody TrainerCreateDto trainerCreateDto) {
        trainerService.create(trainerCreateDto);
        return "trainer create ok";
    }


    @GetMapping("/trainers")
    public List<TrainerResDto> trainers() {
        return trainerService.findAll();
    }

    @PatchMapping("/trainer/update/{id}")
    public String update(@PathVariable Long id, @RequestBody TrainerReqDto trainerReqDto) {
        trainerService.update(id, trainerReqDto);
        return "trainer update Ok";
    }

    @DeleteMapping("/trainer/delete/{id}")
    public String delete(@PathVariable Long id) {
        trainerService.delete(id);
        return "trainer delete Ok";
    }
}