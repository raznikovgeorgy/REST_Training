package com.syncretis.rest_training.service.user;

import com.syncretis.rest_training.exception.UserNotFoundException;
import com.syncretis.rest_training.model.Role;
import com.syncretis.rest_training.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserDetailsServiceImpl service;

    @Test
    void ShouldLoadUserByUsername() {
        //GIVEN
        UserDetails expected = UserDetailsImpl.getUser(initUserData());
        when(userService.findUserByName("user")).thenReturn(initUserData());
        //WHEN
        UserDetails actual = service.loadUserByUsername("user");
        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowUserNotFoundException() {
        //GIVEN
        when(userService.findUserByName(any())).thenThrow(UserNotFoundException.class);
        //THEN
        assertThatThrownBy(() -> service.loadUserByUsername("invalidUserName"))
                .isInstanceOf(UserNotFoundException.class);
    }

    private User initUserData() {
        com.syncretis.rest_training.model.User result = new User();
        result.setId(1L);
        result.setName("user");
        result.setPassword("password");
        result.setCity("Moscow");
        result.setRole(Role.USER);
        return result;
    }
}