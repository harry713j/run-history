package com.harihara.runHistory.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harihara.runHistory.model.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// this class will load the json data to the database when application start
@Component
public class LoadRunJSONData implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LoadRunJSONData.class);
    private final ObjectMapper mapper;
    private final RunRepository repository;

    @Autowired
    public LoadRunJSONData(ObjectMapper mapper, RunRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // if the number of rows in table is zero then we will load

        if (repository.count() == 0){
            try(InputStream stream = TypeReference.class.getResourceAsStream("/data/run-data.json")) {
                List<Run> runs = mapper.readValue(stream, new TypeReference<List<Run>>() {
                });

                log.info("{} rows loaded to run table in postgres!", runs.size());

                repository.saveAll(runs);
            }catch (IOException e){
                throw new RuntimeException("Failed to read from JSON file : " + e.getMessage());
            }
        }else {
            log.info("The run table already have some rows!");
        }
    }
}
