package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.DailyLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DailyLogRepository extends CrudRepository<DailyLog, Integer> {

    @Query
    List<DailyLog> findAllByDateBefore(Date date);

    @Query
    List<DailyLog> findAllByDateBetween(Date date, Date date2);
}
