package com.harihara.runHistory.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Run {
    @Id
    private Integer runId;
    private String title;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Integer miles;
    private Location location;
}
