package com.app.restaurantbooking.repository;

import com.app.restaurantbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "select * from muserverdb.bookings where DATE(start_date_time)= :day", nativeQuery = true)
    List<Booking> findAllByStartDateTime(@Param("day") String dateTime);
}
