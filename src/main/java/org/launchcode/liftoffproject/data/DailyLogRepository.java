package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.DailyLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyLogRepository extends CrudRepository<DailyLog, Integer> {
}
