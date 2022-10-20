package com.app.restaurantbooking.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private Integer tableSize;
    @Column(nullable = false)
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
