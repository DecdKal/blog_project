package web_project.blog.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import web_project.blog.model.dto.RegistrationDTO;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.model.entity.UserRoleEntity;
import web_project.blog.model.enums.UserRoleEnum;
import web_project.blog.repository.RoleRepository;
import web_project.blog.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final UserRoleEntity USER_ROLE = new UserRoleEntity().setRole(UserRoleEnum.USER);

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private RoleRepository mockRoleRepository;

    @BeforeEach
    void setUp() {

        toTest = new UserServiceImpl(
                new ModelMapper(),
                mockPasswordEncoder,
                mockUserRepository,
                mockRoleRepository
        );

    }

    @Test
    void testUserRegistration() {
        // Arrange

        RegistrationDTO registrationDTO =
                new RegistrationDTO()
                        .setUsername("gosho")
                        .setEmail("user@user.com")
                        .setPassword("123");

        Mockito.when(mockPasswordEncoder.encode(registrationDTO.getPassword()))
                .thenReturn(registrationDTO.getPassword()+registrationDTO.getPassword());

        Mockito.when(mockRoleRepository.getUserRoleEntityByName(UserRoleEnum.USER))
                .thenReturn(USER_ROLE);


        // ACT
        toTest.registerUser(registrationDTO);

        // Assert
        Mockito.verify(mockUserRepository).save(userEntityCaptor.capture());

        UserEntity actualSavedEntity = userEntityCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(registrationDTO.getPassword() + registrationDTO.getPassword(),
                actualSavedEntity.getPassword());
        Assertions.assertEquals(registrationDTO.getEmail(), actualSavedEntity.getEmail());
    }
}
