package com.example.parkingproject.model;

import java.time.LocalDateTime;

public record Reservation(
        int id,
        int spaceId,
        String requesterName,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}