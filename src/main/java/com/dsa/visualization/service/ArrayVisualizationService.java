package com.dsa.visualization.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dsa.visualization.dto.ArrayStateResponse;

@Service
public class ArrayVisualizationService {

    private final List<Integer> currentArray = new ArrayList<>();

    public synchronized ArrayStateResponse create(List<Integer> values) {
        validateList(values);
        currentArray.clear();
        currentArray.addAll(values);
        return toResponse("Array initialized.");
    }

    public synchronized ArrayStateResponse read() {
        return toResponse("Current array fetched.");
    }

    public synchronized ArrayStateResponse update(int index, Integer value) {
        validateIndex(index);
        validateValue(value);
        currentArray.set(index, value);
        return toResponse("Value updated at index " + index + ".");
    }

    public synchronized ArrayStateResponse delete(int index) {
        validateIndex(index);
        currentArray.remove(index);
        return toResponse("Value removed at index " + index + ".");
    }

    private ArrayStateResponse toResponse(String message) {
        List<Integer> snapshot = List.copyOf(currentArray);
        return new ArrayStateResponse(snapshot, snapshot.size(), message);
    }

    private void validateList(List<Integer> values) {
        if (values == null) {
            throw new IllegalArgumentException("Array values cannot be null.");
        }
        if (values.stream().anyMatch(v -> v == null)) {
            throw new IllegalArgumentException("Array values cannot contain null.");
        }
    }

    private void validateValue(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= currentArray.size()) {
            throw new IllegalArgumentException("Index out of range. Current size: " + currentArray.size());
        }
    }
}
