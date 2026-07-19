package com.dsa.visualization.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dsa.visualization.dto.StateResponse;

@Service
public class QueueVisualizationService {
    private final Queue<Integer> queue = new LinkedList<>();

        public synchronized StateResponse enqueue(Integer value) {
        if (value == null) throw new IllegalArgumentException("Value cannot be null");
        queue.add(value);
        return toResponse("Value " + value + " enqueued.");
    }

    public synchronized StateResponse dequeue() {
        if (queue.isEmpty()) throw new IllegalArgumentException("Queue Underflow: Cannot dequeue from empty queue.");
        Integer removed = queue.poll();
        return toResponse("Value " + removed + " dequeued.");
    }

    public synchronized StateResponse peek() {
        if (queue.isEmpty()) throw new IllegalArgumentException("Queue is empty.");
        Integer head = queue.peek();
        return toResponse("Front element is: " + head);
    }

    public synchronized StateResponse create(List<Integer> values) {
        if (values == null) throw new IllegalArgumentException("Values cannot be null");
        queue.clear();
        queue.addAll(values);
        return toResponse("Queue initialized with " + values.size() + " elements.");
    }

    private StateResponse toResponse(String message) {
        List<Integer> snapshot = new ArrayList<>(queue);
        return new StateResponse(snapshot, snapshot.size(), message);
    }
}
