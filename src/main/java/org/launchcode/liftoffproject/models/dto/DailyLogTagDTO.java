package org.launchcode.liftoffproject.models.dto;

import com.sun.istack.NotNull;
import org.launchcode.liftoffproject.models.DailyLog;
import org.launchcode.liftoffproject.models.Tag;

public class DailyLogTagDTO {

    @NotNull
    private DailyLog dailyLog;

    @NotNull
    private Tag tag;

    public DailyLog getDailyLog() {
        return dailyLog;
    }

    public void setDailyLog(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
