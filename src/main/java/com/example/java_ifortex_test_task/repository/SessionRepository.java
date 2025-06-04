package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = """
    SELECT s.id,
           s.user_id,
           s.started_at_utc,
           s.ended_at_utc,
           (s.device_type - 1) AS device_type
    FROM sessions s
    WHERE device_type = ?1
    ORDER BY started_at_utc
    LIMIT 1
    """, nativeQuery = true)
    Session getFirstDesktopSession(int deviceTypeCode);

    @Query(value = """
    SELECT s.id,
           s.user_id,
           s.started_at_utc,
           s.ended_at_utc,
           (s.device_type - 1) AS device_type
    FROM sessions s
             JOIN users u ON s.user_id = u.id
    WHERE u.deleted = false
    AND s.ended_at_utc IS NOT NULL
    AND s.ended_at_utc < ?1
    ORDER BY s.started_at_utc DESC
    """, nativeQuery = true)
    List<Session> getSessionsFromActiveUsersEndedBefore2025(Timestamp endTimestamp);
}