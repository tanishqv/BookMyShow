package com.scaler.BookMyShow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SeatType extends BaseModel {
    private String value;
    private Boolean reclinerAvailability;
}
