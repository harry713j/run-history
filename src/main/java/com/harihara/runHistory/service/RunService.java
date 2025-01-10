package com.harihara.runHistory.service;

import com.harihara.runHistory.model.Run;
import com.harihara.runHistory.repo.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RunService {
    private final RunRepository repo;

    @Autowired
    public RunService(RunRepository repo){
        this.repo = repo;
    }

    public List<Run> getAllRun(){
        return this.repo.findAll();
    }

    public Run getRunById(Integer runId){
        Optional<Run> run = this.repo.findByRunId(runId);

        if (run.isEmpty()){
            throw new RuntimeException("No Run with runId: " + runId + " exists");
        }

        return run.get();
    }

    public List<Run> getRunsByLocation(String location){
        return this.repo.findByLocation(location.toUpperCase());
    }

    public void createRun(Run run){
        this.repo.insert(run);
    }

    public void updateRun(Run run, Integer runId){
        if (this.repo.existsById(runId)){
            run.setRunId(runId);
            this.repo.save(run);
        }
        else
            throw new RuntimeException("No run exists by the Id = " + runId);
    }

    public void deleteRun(Integer runId){
        this.repo.deleteById(runId);
    }
}
