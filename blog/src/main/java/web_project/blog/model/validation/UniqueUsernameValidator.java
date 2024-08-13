package web_project.blog.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String > {

    private final UserRepository userRepository;

    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        } else {
            Optional<UserEntity> user = this.userRepository.findByUsername(value);
            return user.isEmpty();
        }
    }
}
