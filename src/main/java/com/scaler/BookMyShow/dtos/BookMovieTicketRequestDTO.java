package com.scaler.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieTicketRequestDTO {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
}
