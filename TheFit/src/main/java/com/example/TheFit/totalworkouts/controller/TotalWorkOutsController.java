package com.example.TheFit.totalworkouts.controller;

import com.example.TheFit.totalworkouts.dto.TotalWorkOutsReqDto;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsResDto;
import com.example.TheFit.totalworkouts.service.TotalWorkOutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/totalworkouts")
public class TotalWorkOutsController {
    private final TotalWorkOutsService totalWorkOutsService;

    @Autowired
    public TotalWorkOutsController(TotalWorkOutsService totalWorkOutsService) {
        this.totalWorkOutsService = totalWorkOutsService;
    }

    @PostMapping("/create")
    public String create(@RequestBody TotalWorkOutsReqDto totalWorkOutsReqDto){
        totalWorkOutsService.create(totalWorkOutsReqDto);
        return "totalworkouts create ok";
    }

    @GetMapping("/list")
    public List<TotalWorkOutsResDto> findAll(){
        return totalWorkOutsService.findAll();
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody TotalWorkOutsReqDto totalWorkOutsReqDto){
        totalWorkOutsService.update(id, totalWorkOutsReqDto);
        return "totalworkouts update ok";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        totalWorkOutsService.delete(id);
        return "totalworkouts delete ok";
    }
}