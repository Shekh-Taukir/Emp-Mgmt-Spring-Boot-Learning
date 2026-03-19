package com.learnSpringBootCode.Hospital_Mgmt_SB.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if (inputRole == null)
                return true;
        List<String> roles = List.of("User", "Admin");
        return roles.contains(inputRole);
    }
}
