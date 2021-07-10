package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.DailyLogRepository;
import org.launchcode.liftoffproject.models.DailyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dailyLog")
public class DailyLogController {

    @Autowired
    private DailyLogRepository dailyLogRepository;

    @GetMapping("add")
    public String displayAddDailyLog(Model model) {
        model.addAttribute("title", "Add Daily Log");
        model.addAttribute(new DailyLog());
        return "dailyLog/add";
    }

    @PostMapping("add")
    public String processAddDailyLog(@ModelAttribute DailyLog newDailyLog) {
        dailyLogRepository.save(newDailyLog);
        return "dailyLog/view";
    }
}
