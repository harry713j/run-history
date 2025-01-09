package com.harihara.runHistory.controller;

import com.harihara.runHistory.model.Run;
import com.harihara.runHistory.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/runs")
public class RunController {

    @Autowired
    private RunService service;

    @GetMapping("")
    public List<Run> getRuns(){
        return service.getAllRun();
    }

    @GetMapping("/{id}")
    public Run getRunById(@PathVariable("id") Integer runId){
        return service.getRunById(runId);
    }

    @GetMapping("/location/{location}")
    public List<Run> getRunByLocation(@PathVariable String location){
        return service.getRunsByLocation(location);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void registerRun(@RequestBody Run run){
        service.createRun(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateRun(@RequestBody Run run,@PathVariable("id") Integer runId){
        service.updateRun(run, runId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void removeRun(@PathVariable("id") Integer runId){
        service.deleteRun(runId);
    }
}
