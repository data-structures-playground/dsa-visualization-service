package com.dsa.visualization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.visualization.dto.ArrayCrudRequest;
import com.dsa.visualization.dto.ArrayElementUpdateRequest;
import com.dsa.visualization.dto.ArrayStateResponse;
import com.dsa.visualization.service.ArrayVisualizationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/arrays")
@RequiredArgsConstructor
public class ArrayVisualizationController {

    private final ArrayVisualizationService arrayVisualizationService;

    @PostMapping
    public ResponseEntity<ArrayStateResponse> create(@RequestBody ArrayCrudRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(arrayVisualizationService.create(request.values()));
    }

    @GetMapping
    public ResponseEntity<ArrayStateResponse> read() {
        return ResponseEntity.ok(arrayVisualizationService.read());
    }

    @PutMapping("/{index}")
    public ResponseEntity<ArrayStateResponse> update(@PathVariable int index, @RequestBody ArrayElementUpdateRequest request) {
        return ResponseEntity.ok(arrayVisualizationService.update(index, request.value()));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<ArrayStateResponse> delete(@PathVariable int index) {
        return ResponseEntity.ok(arrayVisualizationService.delete(index));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ArrayStateResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ArrayStateResponse(null, 0, ex.getMessage()));
    }
}
