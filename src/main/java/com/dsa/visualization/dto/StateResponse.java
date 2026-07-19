package com.dsa.visualization.dto;

import java.util.List;

public record StateResponse(List<Integer> values, int size, String message) {
}
