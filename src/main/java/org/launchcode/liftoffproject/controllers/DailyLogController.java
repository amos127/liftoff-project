package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.DailyLogRepository;
import org.launchcode.liftoffproject.models.DailyLog;
import org.launchcode.liftoffproject.models.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @GetMapping("all")
    public String displayAll(Model model) {
        model.addAttribute("dailyLogs", dailyLogRepository.findAll());
        model.addAttribute("january", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-01-01"), Date.valueOf("2021-01-31")));
        model.addAttribute("february", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-02-01"), Date.valueOf("2021-02-28")));
        model.addAttribute("march", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-03-01"), Date.valueOf("2021-03-31")));
        model.addAttribute("april", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-04-01"), Date.valueOf("2021-04-30")));
        model.addAttribute("may", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-05-01"), Date.valueOf("2021-05-31")));
        model.addAttribute("june", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-06-01"), Date.valueOf("2021-06-30")));
        model.addAttribute("july", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-07-01"), Date.valueOf("2021-07-31")));
        model.addAttribute("august", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-08-01"), Date.valueOf("2021-08-31")));
        model.addAttribute("september", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-09-01"), Date.valueOf("2021-09-30")));
        model.addAttribute("october", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-10-01"), Date.valueOf("2021-10-31")));
        model.addAttribute("november", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-11-01"), Date.valueOf("2021-11-30")));
        model.addAttribute("december", dailyLogRepository.findAllByDateBetween(Date.valueOf("2021-12-01"), Date.valueOf("2021-12-31")));
        return "dailyLog/all";
    }

    @GetMapping("summary")
    public String displaySummary(Model model) {
        model.addAttribute("dailyLogs", dailyLogRepository.findAll());
        model.addAttribute("breakfastStreak", Summary.maxBreakfastStreak(dailyLogRepository.findAll()));
        model.addAttribute("threeMealsStreak", Summary.maxThreeMealsStreak(dailyLogRepository.findAll()));
        model.addAttribute("noAlcoholStreak", Summary.maxZeroAlcoholStreak(dailyLogRepository.findAll()));
        model.addAttribute("moodBreakfastTrue",
                Summary.averageMoodByBreakfast(dailyLogRepository.findAll(), true));
        model.addAttribute("moodBreakfastFalse",
                Summary.averageMoodByBreakfast(dailyLogRepository.findAll(), false));
        model.addAttribute("energyBreakfastTrue",
                Summary.averageEnergyByBreakfast(dailyLogRepository.findAll(), true));
        model.addAttribute("energyBreakfastFalse",
                Summary.averageEnergyByBreakfast(dailyLogRepository.findAll(), false));
        model.addAttribute("avgMoodBreakfastExercise",
                Summary.averageMoodBreakfastExercise(dailyLogRepository.findAll()));
        model.addAttribute("avgMoodBreakfastExerciseFalse",
                Summary.averageMoodBreakfastExerciseFalse(dailyLogRepository.findAll()));
        model.addAttribute("avgEnergyBreakfastExercise",
                Summary.averageEnergyBreakfastExercise(dailyLogRepository.findAll()));
        model.addAttribute("avgEnergyBreakfastExerciseFalse",
                Summary.averageEnergyBreakfastExerciseFalse(dailyLogRepository.findAll()));
        return "dailyLog/summary";
    }
}
