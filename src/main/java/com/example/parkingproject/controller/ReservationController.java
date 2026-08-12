package com.example.parkingproject.controller;

import com.example.parkingproject.model.Reservation;
import com.example.parkingproject.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 1. Create a reservation
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        try {
            reservationService.createReservation(reservation);
            return ResponseEntity.ok("Reservation successfully created.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            // If our time-conflict logic throws an error, return a 400 Bad Request to the user
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2. Get reservations for a specific space
    @GetMapping("/space/{spaceId}")
    public ResponseEntity<List<Reservation>> getReservationsBySpace(@PathVariable int spaceId) {
        List<Reservation> reservations = reservationService.getReservationsForSpace(spaceId);
        return ResponseEntity.ok(reservations);
    }

    // 3. Cancel a reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable int id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok("Reservation cancelled successfully.");
    }
}