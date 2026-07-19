package com.dsa.visualization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dsa.visualization.service.QueueVisualizationService;
import com.dsa.visualization.dto.StateResponse;

@RestController
@RequestMapping("/vis-api/v1/queue")
public class QueueController {

    private final QueueVisualizationService queueService;

    public QueueController(QueueVisualizationService queueService) {
        this.queueService = queueService;
    }

        @PostMapping
    public ResponseEntity<StateResponse> create(@RequestBody java.util.List<Integer> values) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(queueService.create(values));
    }

    @PostMapping("/enqueue")
    public ResponseEntity<StateResponse> enqueue(@RequestBody Integer value) {
        return ResponseEntity.ok(queueService.enqueue(value));
    }

    @PostMapping("/dequeue")
    public ResponseEntity<StateResponse> dequeue() {
        return ResponseEntity.ok(queueService.dequeue());
    }

    @GetMapping("/peek")
    public ResponseEntity<StateResponse> peek() {
        return ResponseEntity.ok(queueService.peek());
    }
}
