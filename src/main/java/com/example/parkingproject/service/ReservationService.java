package com.example.parkingproject.service;

import com.example.parkingproject.model.Reservation;
import com.example.parkingproject.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(Reservation newReservation) {
        // 1. Basic validation
        if (newReservation.endTime().isBefore(newReservation.startTime())) {
            throw new IllegalArgumentException("End time must be after start time.");
        }

        // 2. Fetch all existing reservations for this specific parkingproject space
        List<Reservation> existingReservations = reservationRepository.findReservationsBySpaceId(newReservation.spaceId());

        // 3. Check for time overlaps
        for (Reservation existing : existingReservations) {
            boolean isOverlapping = newReservation.startTime().isBefore(existing.endTime()) &&
                    newReservation.endTime().isAfter(existing.startTime());

            if (isOverlapping) {
                throw new IllegalStateException("Parking space is already booked during this time.");
            }
        }

        // 4. If we made it here, there are no overlaps! Save it to the database.
        reservationRepository.save(newReservation);
    }

    public List<Reservation> getReservationsForSpace(int spaceId) {
        return reservationRepository.findReservationsBySpaceId(spaceId);
    }

    public void cancelReservation(int reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}