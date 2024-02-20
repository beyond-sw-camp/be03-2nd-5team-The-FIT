package com.example.TheFit.user.trainer.controller;

import com.example.TheFit.common.TheFitResponse;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.dto.TrainerReqDto;
import com.example.TheFit.user.trainer.dto.TrainerResDto;
import com.example.TheFit.user.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TheFitResponse> create(@Valid @RequestBody TrainerReqDto trainerReqDto) {
        Trainer trainer = trainerService.create(trainerReqDto);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success create",trainer.getId()),HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<TheFitResponse> trainers() {
        List<TrainerResDto> trainerResDtos = trainerService.findAll();
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success check",trainerResDtos),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TheFitResponse> update(@PathVariable Long id, @Valid @RequestBody TrainerReqDto trainerReqDto) {
        Trainer trainer = trainerService.update(id, trainerReqDto);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success update",trainer.getId()),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TheFitResponse> delete(@Valid @PathVariable Long id) {
        trainerService.delete(id);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success delete",null),HttpStatus.OK);
    }
}