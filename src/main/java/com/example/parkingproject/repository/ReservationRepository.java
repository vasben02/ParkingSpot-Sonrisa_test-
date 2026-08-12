package com.example.parkingproject.repository;

import com.example.parkingproject.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Reservation> rowMapper = (rs, rowNum) -> new Reservation(
            rs.getInt("id"),
            rs.getInt("space_id"),
            rs.getString("requester_name"),
            rs.getTimestamp("start_time").toLocalDateTime(),
            rs.getTimestamp("end_time").toLocalDateTime()
    );

    public List<Reservation> findReservationsBySpaceId(int spaceId) {
        String sql = "SELECT * FROM reservations WHERE space_id = ?";
        return jdbcTemplate.query(sql, rowMapper, spaceId);
    }

    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservations (space_id, requester_name, start_time, end_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                reservation.spaceId(),
                reservation.requesterName(),
                reservation.startTime(),
                reservation.endTime()
        );
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}