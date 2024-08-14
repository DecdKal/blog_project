package web_project.blog.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.model.entity.UserRoleEntity;
import web_project.blog.model.enums.UserRoleEnum;
import web_project.blog.model.user.PUserDetails;
import web_project.blog.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
    public class PUserDetailsServiceTest {

    private static final String TEST_EMAIL = "user@user.com";
    private static final String MISSING_TEST_EMAIL = "fake@fake.com";

    private PUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new PUserDetailsService(mockUserRepository);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {

        // Arrange
        UserEntity testUser = new UserEntity()
                .setEmail(TEST_EMAIL)
                .setPassword("1234")
                .setUsername("gosho")
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));

        when(mockUserRepository.findByEmail(TEST_EMAIL))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails userDetails = toTest.loadUserByUsername(TEST_EMAIL);

        // Assert
        Assertions.assertInstanceOf(PUserDetails.class, userDetails);

        PUserDetails UserDetails = (PUserDetails) userDetails;

        Assertions.assertEquals(TEST_EMAIL, userDetails.getUsername());
        Assertions.assertEquals(testUser.getUsername(), ((PUserDetails) userDetails).getNickName());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());

        var expectedRoles = testUser.getRoles().stream().map(UserRoleEntity::getRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(MISSING_TEST_EMAIL)
        );
    }

}
