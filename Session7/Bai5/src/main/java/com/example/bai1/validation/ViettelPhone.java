package com.example.bai1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.ElementType;
import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ViettelPhoneValidator.class)
@Documented
public @interface ViettelPhone {
    String message() default "Số điện thoại không phải Viettel";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
