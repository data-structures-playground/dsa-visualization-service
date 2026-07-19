package com.dsa.visualization.service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dsa.visualization.dto.StateResponse;

@Service
public class StackVisualizationService {
    private final Deque<Integer> stack = new ArrayDeque<>();

        public synchronized StateResponse push(Integer value) {
        if (value == null) throw new IllegalArgumentException("Value cannot be null");
        stack.push(value);
        return toResponse("Value " + value + " pushed onto stack.");
    }

    public synchronized StateResponse pop() {
        if (stack.isEmpty()) throw new IllegalArgumentException("Stack Underflow: Cannot pop from empty stack.");
        Integer popped = stack.pop();
        return toResponse("Value " + popped + " popped from stack.");
    }

    public synchronized StateResponse peek() {
        if (stack.isEmpty()) throw new IllegalArgumentException("Stack is empty.");
        Integer top = stack.peek();
        return toResponse("Top element is: " + top);
    }

    public synchronized StateResponse create(List<Integer> values) {
        if (values == null) throw new IllegalArgumentException("Values cannot be null");
        stack.clear();
        // Push in reverse order so the first element of the list ends up at the bottom
        for (int i = values.size() - 1; i >= 0; i--) {
            stack.push(values.get(i));
        }
        return toResponse("Stack initialized with " + values.size() + " elements.");
    }

    private StateResponse toResponse(String message) {
        List<Integer> snapshot = new ArrayList<>(stack);
        return new StateResponse(snapshot, snapshot.size(), message);
    }
}
