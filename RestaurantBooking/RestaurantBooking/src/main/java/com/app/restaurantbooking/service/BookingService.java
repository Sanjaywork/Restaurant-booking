package com.app.restaurantbooking.service;

import com.app.restaurantbooking.entity.Booking;
import com.app.restaurantbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> bookingsOfSpecificDate(String date) {
        return bookingRepository.findAllByStartDateTime(date);
    }
}
