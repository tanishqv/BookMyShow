package com.scaler.BookMyShow.repositories;

import com.scaler.BookMyShow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    @Query(
            "SELECT s FROM Show s " +
            "WHERE s.screen.id = :screenId " +
            "AND (" +
            "s.startTime < :proposedEndTime " +
            "AND " +
            "s.endTime > :proposedStartTime " +
            ")"
    )
    List<Show> findAllConflictingShows(
            @Param("screenId") Long screenId,
            @Param("proposedStartTime") Date proposedStartTime,
            @Param("proposedEndTime") Date proposedEndTime
    );
}
