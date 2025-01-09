package com.harihara.runHistory.model;

import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Run {
    private Integer runId;
    private String title;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Integer miles;
    private Location location;
}
