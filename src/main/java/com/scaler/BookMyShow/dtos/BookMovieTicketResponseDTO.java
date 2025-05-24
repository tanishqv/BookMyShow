package com.scaler.BookMyShow.dtos;

import com.scaler.BookMyShow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieTicketResponseDTO {
    Booking booking;
}
