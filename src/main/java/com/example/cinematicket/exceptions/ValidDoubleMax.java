package com.example.cinematicket.exceptions;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DoubleMaxValidator.class)
public @interface ValidDoubleMax {
    String message() default "Giá trị nhập vào quá lớn";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double max() default Double.MAX_VALUE;
}
