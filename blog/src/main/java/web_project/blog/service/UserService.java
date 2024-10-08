package web_project.blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import web_project.blog.model.dto.RegistrationDTO;
import web_project.blog.model.dto.UserProfileDTO;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.model.user.PUserDetails;

import java.util.Optional;

public interface UserService {

    void registerUser(RegistrationDTO registrationDTO);

    Optional<PUserDetails> getCurrentUser();

    Optional<UserEntity> getUserByEmail(String email);

    UserProfileDTO getUserByEmailAndMapToDTO(String email);

    void updateUser(UserProfileDTO userProfileDTO, PUserDetails userDetails);


}
