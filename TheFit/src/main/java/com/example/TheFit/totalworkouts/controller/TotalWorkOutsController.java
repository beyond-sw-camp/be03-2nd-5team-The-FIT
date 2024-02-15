package com.example.TheFit.totalworkouts.controller;

import com.example.TheFit.member.dto.MemberResDto;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsDto;
import com.example.TheFit.totalworkouts.service.TotalWorkOutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String create(@Valid @RequestBody TotalWorkOutsDto totalWorkOutsDto){
        totalWorkOutsService.create(totalWorkOutsDto);
        return "totalworkouts create ok";
    }
    @GetMapping("/list")
    public List<TotalWorkOutsDto> TotalWorkOuts(){
        return totalWorkOutsService.findAll();
    }
    @PatchMapping("/update/{id}")
    public String update(@Valid @PathVariable Long id, @RequestBody TotalWorkOutsDto totalWorkOutsDto){
        totalWorkOutsService.update(id, totalWorkOutsDto);
        return "totalworkouts update ok";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@Valid @PathVariable Long id) {
        totalWorkOutsService.delete(id);
        return "totalworkouts delete ok";
    }
}
