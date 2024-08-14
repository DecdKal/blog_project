package project.blog.events.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateNotInThePastValidator implements ConstraintValidator<DateNotInThePast, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        } else {
            LocalDate currentDate= LocalDate.now();
            return  value.isAfter(currentDate);
        }
    }
}
