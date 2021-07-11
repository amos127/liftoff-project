package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.DailyLogRepository;
import org.launchcode.liftoffproject.models.DailyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("view/{id}")
    public String viewById(Model model, @PathVariable int id) {
        Optional optDailyLog = dailyLogRepository.findById(id);
        if (optDailyLog.isPresent()) {
            DailyLog dailyLog = (DailyLog) optDailyLog.get();
            model.addAttribute("dailyLog", dailyLog);
            return "dailyLog/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("edit/{id}")
    public String editDailyLog(Model model, @PathVariable int id) {
        Optional optDailyLog = dailyLogRepository.findById(id);
        if (optDailyLog.isPresent()) {
            DailyLog dailyLog = (DailyLog) optDailyLog.get();
            model.addAttribute("dailyLog", dailyLog);
            return "dailyLog/edit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("edit/{id}")
    public String processEditDailyLog(@PathVariable int id, @ModelAttribute DailyLog dailyLog) {
        dailyLog.setId(id);
        dailyLogRepository.save(dailyLog);
        return "dailyLog/view";
    }

    @GetMapping("delete/{id}")
    public String deleteDailyLog(@PathVariable int id) {
        dailyLogRepository.deleteById(id);
        return "index";
    }

    @GetMapping("summary")
    public String displaySummary(Model model) {
        model.addAttribute("dailyLogs", dailyLogRepository.findAll());
        return "dailyLog/summary";
    }
}
