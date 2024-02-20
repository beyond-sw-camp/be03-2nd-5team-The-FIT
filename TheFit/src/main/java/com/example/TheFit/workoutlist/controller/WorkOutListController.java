package com.example.TheFit.workoutlist.controller;

import com.example.TheFit.common.TheFitResponse;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.dto.WorkOutListReqDto;
import com.example.TheFit.workoutlist.dto.WorkOutListResDto;
import com.example.TheFit.workoutlist.service.WorkOutListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("workout_list")
public class WorkOutListController {
    private final WorkOutListService workOutListService;

    @Autowired
    public WorkOutListController(WorkOutListService workOutListService) {
        this.workOutListService = workOutListService;
    }

    @PostMapping("/create")
    public ResponseEntity<TheFitResponse> create(@Valid @RequestBody WorkOutListReqDto workOutListReqDto){
        WorkOutList workOutList = workOutListService.create(workOutListReqDto);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success create",workOutList.getId()),HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<TheFitResponse> findALl(){
        List<WorkOutListResDto> workOutListResDtoList = workOutListService.findAll();
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success create",workOutListResDtoList),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TheFitResponse> delete(@PathVariable Long id) {
        workOutListService.delete(id);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success delete",null),HttpStatus.OK);
    }
}