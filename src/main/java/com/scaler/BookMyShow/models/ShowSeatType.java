package com.scaler.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel {
    private Double price;
    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
}
