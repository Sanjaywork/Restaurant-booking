package com.app.restaurantbooking.controller;

import com.app.restaurantbooking.entity.Booking;
import com.app.restaurantbooking.service.BookingService;
import io.muserver.Method;
import io.muserver.MuServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.muserver.MuServerBuilder.httpServer;

@Component
@Slf4j
public class BookingController {

    @Autowired
    BookingService bookingService;

    public void server() {
        MuServer server = httpServer()
                //This handler will return all the bookings in the database.
                .addHandler(Method.GET, "/getAllBookings", (request, response, pathParams) -> {
                    List<Booking> bookings = bookingService.getAllBookings();
                    response.write(bookings.toString());
                })
                //This handler will return all the bookings of a specific date. We will have to give the date.
                .addHandler(Method.GET, "/getBooking/{date}", (request, response, pathParams) -> {
                    log.info("Incoming date request: {}", pathParams.get("date"));
                    List<Booking> bookings = bookingService.bookingsOfSpecificDate(pathParams.get("date"));
                    response.contentType("application/json");
                    response.write(String.valueOf(bookings));
                })
                //This handler will be used to book a table in restaurant
                .addHandler(Method.POST, "/bookTable", (request, response, pathParams) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String startDateTime = request.query().get("startDateTime");
                    LocalDateTime bookingDateTime = LocalDateTime.parse(startDateTime, formatter);
                    Booking booking = Booking.builder()
                            .customerName(request.query().get("customerName"))
                            .tableSize(Integer.parseInt(request.query().get("tableSize")))
                            .startDateTime(bookingDateTime)
                            .endDateTime(bookingDateTime.plusHours(2))
                            .build();
                    Booking savedBooking = bookingService.save(booking);
                    log.info("Incoming booking request, {}", booking);
                    response.contentType("application/json");
                    response.write("Your booking details: " + savedBooking);
                })
                .start();
        System.out.println("Your Server started at " + server.uri());
    }
}
