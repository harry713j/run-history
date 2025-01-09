package com.harihara.runHistory.repo;

import com.harihara.runHistory.model.Run;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private JdbcClient jdbcClient;

    @Autowired
    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public void create(Run run){
        var created = jdbcClient.sql("INSERT INTO run(run_id, title, started_on," +
                " finished_on, miles, location) VALUES (?, ?, ?, ? , ?, ?)")
                .param(run.getRunId())
                .param(run.getTitle())
                .param(run.getStartedOn())
                .param(run.getFinishedOn())
                .param(run.getMiles())
                .param(run.getLocation().toString())
                .update();

        Assert.state(created == 1, "Failed create run " + run.getTitle());
    }

    public List<Run> findAll(){
        return jdbcClient.sql("SELECT * FROM run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer runId){
        return jdbcClient.sql("SELECT * FROM run WHERE run_id = :runId")
                .param("runId", runId)
                .query(Run.class)
                .optional();
    }

    public void update(Run run, Integer runId){
        var updated = jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, finished_on = ?," +
                " miles = ?, location = ? WHERE run_id = ?")
                .param(run.getTitle())
                .param(run.getStartedOn())
                .param(run.getFinishedOn())
                .param(run.getMiles())
                .param(run.getLocation().toString())
                .param(runId)
                .update();

        Assert.state(updated == 1, "Failed update run " + run.getTitle());
    }

    public void delete(Integer runId){
        var deleted = jdbcClient.sql("DELETE FROM run WHERE run_id = :runId")
                .param("runId", runId)
                .update();

        Assert.state(deleted == 1, "Failed to delete run " + runId);
    }

    public int count(){
        return jdbcClient.sql("SELECT * FROM run")
                .query()
                .listOfRows()
                .size();
    }

    public void saveAll(List<Run> runs){
        runs.forEach(this::create);
    }

    public List<Run> findByLocation(String location){
        return jdbcClient.sql("SELECT * FROM run WHERE location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }
}
