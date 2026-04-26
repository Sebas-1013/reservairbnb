package com.reservairbnb.reservairbnb.repository;

import com.reservairbnb.reservairbnb.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByPropertyIdAndEstadoNotAndFechaCheckInLessThanEqualAndFechaCheckOutGreaterThanEqual(
            Long propertyId,
            String estado,
            LocalDate fechaCheckOut,
            LocalDate fechaCheckIn
    );
}
