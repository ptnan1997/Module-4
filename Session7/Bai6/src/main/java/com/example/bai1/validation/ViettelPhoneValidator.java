package com.example.bai1.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ViettelPhoneValidator implements ConstraintValidator<ViettelPhone,String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null){
            return true;
        }

        return s.matches("^(086|096|097|098|032|033|034|035|036|037|038|039)\\d{7}$");
    }
}
