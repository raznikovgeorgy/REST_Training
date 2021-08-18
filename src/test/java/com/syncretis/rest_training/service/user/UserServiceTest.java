package com.syncretis.rest_training.service.user;

import com.syncretis.rest_training.model.Role;
import com.syncretis.rest_training.model.User;
import com.syncretis.rest_training.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldFindUserByName() {
        //GIVEN
        User expected = initUserData();
        when(userRepository.findByName("user")).thenReturn(java.util.Optional.of(expected));
        //WHEN
        User actual = service.findUserByName("user");
        //THEN
        verify(userRepository).findByName("user");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowUserNotFoundException() {
        //THEN
        assertThatThrownBy(() -> service.findUserByName("invalidUserName"));
    }

    @Test
    void shouldReturnUserFromToken() {
        //GIVEN
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Mjk0ODM2MjksInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJyZWFkIl0sImp0aSI6IjllOWNkYTJkLTlmMTctNGMzNi05MzkxLWYyMzE5Y2E5MTlhNSIsImNsaWVudF9pZCI6InVzZXItY2xpZW50Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.buPyaWhZm5vIDXZDwf13MVwJ1Nplj0zoElBxGFZNlVU";
        User expected = initUserData();
        when(userRepository.findByName("user")).thenReturn(java.util.Optional.of(expected));
        //WHEN
        User actual = service.getUserFromToken(token);
        //THEN
        verify(userRepository).findByName(any());
        assertThat(actual).isEqualTo(expected);
    }

    private User initUserData() {
        User result = new User();
        result.setId(1L);
        result.setName("user");
        result.setPassword("password");
        result.setCity("Moscow");
        result.setRole(Role.USER);
        return result;
    }
}