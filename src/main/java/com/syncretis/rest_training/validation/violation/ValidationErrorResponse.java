package com.syncretis.rest_training.validation.violation;

import java.util.List;

public class ValidationErrorResponse {
    private final List<Violation> violation;

    public ValidationErrorResponse(List<Violation> violation) {
        this.violation = violation;
    }

    public List<Violation> getViolation() {
        return violation;
    }
}

