package com.scaler.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    private String bookingNumber;
    @ManyToOne
    private User bookedBy;
    @OneToMany
    private List<ShowSeat> showSeats;
    private Double amount;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}
