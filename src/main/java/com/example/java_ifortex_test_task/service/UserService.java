package com.example.java_ifortex_test_task.service;

import com.example.java_ifortex_test_task.dto.UserResponseDTO;
import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.User;
import com.example.java_ifortex_test_task.mapper.UserMapper;
import com.example.java_ifortex_test_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final int MOBILE_CODE = DeviceType.MOBILE.getCode();

    // Returns a User with the biggest amount of sessions
    public UserResponseDTO getUserWithMostSessions() {
        User userWithMostSessions = userRepository.getUserWithMostSessions();
        return userMapper.toDto(userWithMostSessions);
    }

    // Returns Users that have at least 1 Mobile session
    public List<UserResponseDTO> getUsersWithAtLeastOneMobileSession() {
        return userRepository.getUsersWithAtLeastOneMobileSession(MOBILE_CODE)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
