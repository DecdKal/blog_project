package web_project.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web_project.blog.model.dto.RegistrationDTO;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.model.enums.UserRoleEnum;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.repository.RoleRepository;
import web_project.blog.repository.UserRepository;
import web_project.blog.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerUser(RegistrationDTO registrationDTO) {
        userRepository.save(map(registrationDTO));
    }

    @Override
    public Optional<PUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() instanceof PUserDetails pUserDetails) {
            return Optional.of(pUserDetails);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private UserEntity map(RegistrationDTO registrationDTO) {
        UserEntity mappedEntity = modelMapper.map(registrationDTO, UserEntity.class);

        mappedEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        mappedEntity.setRoles(List.of(roleRepository.getUserRoleEntityByName(UserRoleEnum.USER)));

        return mappedEntity;
    }
}
