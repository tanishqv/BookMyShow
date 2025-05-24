package com.scaler.BookMyShow.repositories;

import com.scaler.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShow_IdAndSeat_IdIn(Long showId, List<Long> seatIds);
}
