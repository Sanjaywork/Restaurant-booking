package com.app.restaurantbooking;

import com.app.restaurantbooking.controller.BookingController;
import com.app.restaurantbooking.entity.Booking;
import com.app.restaurantbooking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class RestaurantBookingApplication implements CommandLineRunner {

    @Autowired
    BookingController bookingController;

    @Autowired
    BookingService bookingService;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantBookingApplication.class, args);
    }

    /**
     * This method will execute our server
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        //Adding some pre bookings so that we can test our API's
        Booking booking = Booking.builder()
                .id(1)
                .customerName("Name 1")
                .tableSize(10)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(2))
                .build();
        bookingService.save(booking);

        Booking secondBooking = Booking.builder()
                .id(2)
                .customerName("Name 2")
                .tableSize(2)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(2))
                .build();
        bookingService.save(secondBooking);
        bookingController.server();
    }
}
