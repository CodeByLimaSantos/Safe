package com.limasoftware.Safety.repository;

import com.limasoftware.Safety.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {


    @Query("""
    SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
    FROM Scheduling s
    WHERE s.client = :client
      AND s.status = com.limasoftware.Safety.enums.SchedulingStatus.SCHEDULED
      AND (s.startDate < :end AND s.endDate > :start)
      AND (:schedulingId IS NULL OR s.id <> :schedulingId)
    """)


    boolean existsConflict(@Param("client") String client,
                           @Param("start")LocalDateTime start,
                           @Param("end") LocalDateTime end,
                           @Param("schedulingId") Long schedulingId
                            );


}
