package web_project.blog.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.repository.UserRepository;

import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        } else {
            Optional<UserEntity> user = this.userRepository.findByEmail(value);
            return user.isEmpty();
        }
    }
}
