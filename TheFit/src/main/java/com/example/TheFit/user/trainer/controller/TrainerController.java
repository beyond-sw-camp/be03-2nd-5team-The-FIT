package com.example.TheFit.user.trainer.controller;

import com.example.TheFit.user.trainer.dto.TrainerReqDto;
import com.example.TheFit.user.trainer.dto.TrainerResDto;
import com.example.TheFit.user.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/create")
    public String create(@Valid @RequestBody TrainerReqDto trainerReqDto) {
        trainerService.create(trainerReqDto);
        return "trainer create ok";
    }

    @GetMapping("/list")
    public List<TrainerResDto> trainers() {
        return trainerService.findAll();
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @RequestBody TrainerReqDto trainerReqDto) {
        trainerService.update(id, trainerReqDto);
        return "trainer update Ok";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@Valid @PathVariable Long id) {
        trainerService.delete(id);
        return "trainer delete Ok";
    }
}