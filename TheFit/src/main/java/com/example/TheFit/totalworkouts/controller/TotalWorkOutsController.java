package com.example.TheFit.totalworkouts.controller;

import com.example.TheFit.totalworkouts.dto.TotalWorkOutsCreateDto;
import com.example.TheFit.totalworkouts.service.TotalWorkOutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotalWorkOutsController {
    private final TotalWorkOutsService totalWorkOutsService;
    @Autowired
    public TotalWorkOutsController(TotalWorkOutsService totalWorkOutsService) {
        this.totalWorkOutsService = totalWorkOutsService;
    }
    @PostMapping("/totalworkouts/create")
    public String create(@RequestBody TotalWorkOutsCreateDto totalWorkOutsCreateDto){
        totalWorkOutsService.create(totalWorkOutsCreateDto);
        return "totalworkouts create ok";
    }
}
