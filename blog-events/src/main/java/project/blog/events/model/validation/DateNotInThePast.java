package project.blog.events.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateNotInThePastValidator.class)
public @interface DateNotInThePast {

    String message() default "The date must be in the future.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
