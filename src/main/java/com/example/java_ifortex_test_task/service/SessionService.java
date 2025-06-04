package com.example.java_ifortex_test_task.service;

import com.example.java_ifortex_test_task.dto.SessionResponseDTO;
import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.Session;
import com.example.java_ifortex_test_task.mapper.SessionMapper;
import com.example.java_ifortex_test_task.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    private static final int DESKTOP_CODE = DeviceType.DESKTOP.getCode();
    private static final Timestamp TIMESTAMP2025 = Timestamp.valueOf("2025-01-01 00:00:00");

    // Returns the first (earliest) desktop Session
    public SessionResponseDTO getFirstDesktopSession() {
        Session firstDesktopSession = sessionRepository.getFirstDesktopSession(DESKTOP_CODE);
        return sessionMapper.toDto(firstDesktopSession);
    }

    // Returns only Sessions from Active users that were ended before 2025
    public List<SessionResponseDTO> getSessionsFromActiveUsersEndedBefore2025() {
        return sessionRepository.getSessionsFromActiveUsersEndedBefore2025(TIMESTAMP2025)
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }
}