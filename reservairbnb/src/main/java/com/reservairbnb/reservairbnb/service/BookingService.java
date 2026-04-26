package com.reservairbnb.reservairbnb.service;

import com.reservairbnb.reservairbnb.model.Booking;
import com.reservairbnb.reservairbnb.model.Property;
import com.reservairbnb.reservairbnb.model.User;
import com.reservairbnb.reservairbnb.repository.BookingRepository;
import com.reservairbnb.reservairbnb.repository.PropertyRepository;
import com.reservairbnb.reservairbnb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Booking> obtenerTodas() {
        return bookingRepository.findAll();
    }

    public Booking guardarReserva(Booking booking) {

        User user = userRepository.findById(booking.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Property property = propertyRepository.findById(booking.getProperty().getId())
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));

        validarFechas(booking.getFechaCheckIn(), booking.getFechaCheckOut());
        validarPropiedadNoOcupada(property.getId(), booking.getFechaCheckIn(), booking.getFechaCheckOut());
        validarQueNoSeaSuPropiaPropiedad(user.getId(), property.getUser().getId());

        booking.setUser(user);
        booking.setProperty(property);

        if (booking.getEstado() == null || booking.getEstado().isBlank()) {
            booking.setEstado("PENDIENTE");
        }

        return bookingRepository.save(booking);
    }

    public Booking cancelarReserva(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        validarCancelacionCon24Horas(booking.getFechaCheckIn());

        booking.setEstado("CANCELADA");
        return bookingRepository.save(booking);
    }

    private void validarFechas(LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
        if (!fechaCheckIn.isBefore(fechaCheckOut)) {
            throw new RuntimeException("La fecha de entrada debe ser anterior a la fecha de salida.");
        }
    }

    private void validarPropiedadNoOcupada(Long propertyId, LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
        List<Booking> reservasExistentes =
                bookingRepository.findByPropertyIdAndEstadoNotAndFechaCheckInLessThanEqualAndFechaCheckOutGreaterThanEqual(
                        propertyId,
                        "CANCELADA",
                        fechaCheckOut,
                        fechaCheckIn
                );

        if (!reservasExistentes.isEmpty()) {
            throw new RuntimeException("No se puede reservar la propiedad porque ya está ocupada en esas fechas.");
        }
    }

    private void validarQueNoSeaSuPropiaPropiedad(Long userId, Long hostId) {
        if (userId.equals(hostId)) {
            throw new RuntimeException("Un usuario no puede reservar su propia propiedad.");
        }
    }

    private void validarCancelacionCon24Horas(LocalDate fechaCheckIn) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaEntrada = fechaCheckIn.atStartOfDay();

        long horas = ChronoUnit.HOURS.between(ahora, fechaEntrada);

        if (horas < 24) {
            throw new RuntimeException("No se puede cancelar una reserva si faltan menos de 24 horas para el check-in.");
        }
    }
}
