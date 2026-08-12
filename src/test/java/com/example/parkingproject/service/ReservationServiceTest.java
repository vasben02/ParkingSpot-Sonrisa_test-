package com.example.parkingproject.service;

import com.example.parkingproject.model.Reservation;
import com.example.parkingproject.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    // 1. Create a fake version of the database repository
    @Mock
    private ReservationRepository reservationRepository;

    // 2. Inject the fake database into our real service
    @InjectMocks
    private ReservationService reservationService;

    @Test
    void createReservation_ShouldThrowException_WhenTimeOverlaps() {
        // Arrange: Tell the fake database to pretend Jane already has a reservation from 11:00 to 13:00
        Reservation existingJane = new Reservation(
                1, 1, "Jane Smith",
                LocalDateTime.of(2026, 8, 20, 11, 0),
                LocalDateTime.of(2026, 8, 20, 13, 0)
        );
        when(reservationRepository.findReservationsBySpaceId(1)).thenReturn(List.of(existingJane));

        // Act: John tries to book the same space from 10:00 to 12:00 (Overlaps by 1 hour)
        Reservation overlappingJohn = new Reservation(
                null, 1, "John Doe",
                LocalDateTime.of(2026, 8, 20, 10, 0),
                LocalDateTime.of(2026, 8, 20, 12, 0)
        );

        // Assert: Verify that our code throws the exact exception we expect
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> reservationService.createReservation(overlappingJohn)
        );

        assertEquals("Parking space is already booked during this time.", exception.getMessage());
    }
}