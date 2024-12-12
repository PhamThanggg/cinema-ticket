package com.example.cinematicket.exceptions;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DoubleMaxValidator implements ConstraintValidator<ValidDoubleMax, Double> {
    private static final double MAX_VALUE = Integer.MAX_VALUE;

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value <= MAX_VALUE;
    }
}
