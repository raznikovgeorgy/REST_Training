package com.syncretis.rest_training.service;

import com.syncretis.rest_training.model.Role;
import com.syncretis.rest_training.model.User;
import com.syncretis.rest_training.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    UserService userService;
    @InjectMocks
    private WeatherService service;

    @Test
    void shouldReturnWeather() {
        //GIVEN
        String token = "aeyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Mjk0ODM2MjksInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJyZWFkIl0sImp0aSI6IjllOWNkYTJkLTlmMTctNGMzNi05MzkxLWYyMzE5Y2E5MTlhNSIsImNsaWVudF9pZCI6InVzZXItY2xpZW50Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.buPyaWhZm5vIDXZDwf13MVwJ1Nplj0zoElBxGFZNlVU";
        when(userService.getUserFromToken(token)).thenReturn(initUserData());
        //WHEN
        String actual = service.getWeather(token);
        //THEN
        assertThat(actual).isNotEmpty();
    }

    private User initUserData() {
        com.syncretis.rest_training.model.User result = new User();
        result.setId(1L);
        result.setName("user");
        result.setPassword("password");
        result.setCity("Tomsk");
        result.setRole(Role.USER);
        return result;
    }
}