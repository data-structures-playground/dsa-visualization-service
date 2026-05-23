package com.dsa.visualization.dto;

import java.util.List;

public record ArrayStateResponse(List<Integer> values, int size, String message) {
}
