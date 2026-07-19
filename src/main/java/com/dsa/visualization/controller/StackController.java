package com.dsa.visualization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dsa.visualization.service.StackVisualizationService;
import com.dsa.visualization.dto.StateResponse;

@RestController
@RequestMapping("/vis-api/v1/stack")
public class StackController {

    private final StackVisualizationService stackService;

    public StackController(StackVisualizationService stackService) {
        this.stackService = stackService;
    }

        @PostMapping
    public ResponseEntity<StateResponse> create(@RequestBody java.util.List<Integer> values) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(stackService.create(values));
    }

    @PostMapping("/push")
    public ResponseEntity<StateResponse> push(@RequestBody Integer value) {
        return ResponseEntity.ok(stackService.push(value));
    }

    @PostMapping("/pop")
    public ResponseEntity<StateResponse> pop() {
        return ResponseEntity.ok(stackService.pop());
    }

    @GetMapping("/peek")
    public ResponseEntity<StateResponse> peek() {
        return ResponseEntity.ok(stackService.peek());
    }
}
