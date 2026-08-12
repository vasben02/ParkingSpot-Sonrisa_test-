package com.example.parkingproject.model;

import java.time.LocalDateTime;

public record Reservation(
        Integer id,
        Integer spaceId,
        String requesterName,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}