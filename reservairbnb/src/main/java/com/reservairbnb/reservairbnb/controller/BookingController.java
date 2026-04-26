package com.reservairbnb.reservairbnb.controller;

import com.reservairbnb.reservairbnb.model.Booking;
import com.reservairbnb.reservairbnb.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.obtenerTodas();
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.guardarReserva(booking);
    }

    @PatchMapping("/{id}/cancelar")
    public Booking cancelarReserva(@PathVariable Long id) {
        return bookingService.cancelarReserva(id);
    }
}