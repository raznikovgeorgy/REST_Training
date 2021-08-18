package com.syncretis.rest_training.service.user;

import com.syncretis.rest_training.model.User;
import com.syncretis.rest_training.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public User findUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
    }

    public User getUserFromToken(String token) {
        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String decodedData = new String(decoder.decode(chunks[1]));
        String substringUserName = decodedData.substring(decodedData.indexOf("user_name"));
        String username = substringUserName.substring(12, substringUserName.indexOf(",") - 1);
        return findUserByName(username);
    }
}