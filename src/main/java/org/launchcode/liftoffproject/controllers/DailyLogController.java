package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.auth.UserService;
import org.launchcode.liftoffproject.data.DailyLogRepository;
import org.launchcode.liftoffproject.data.TagRepository;
import org.launchcode.liftoffproject.data.UserRepository;
import org.launchcode.liftoffproject.models.DailyLog;
import org.launchcode.liftoffproject.models.Stats;
import org.launchcode.liftoffproject.models.Tag;
import org.launchcode.liftoffproject.models.User;
import org.launchcode.liftoffproject.models.dto.DailyLogTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("dailyLog")
public class DailyLogController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private DailyLogRepository dailyLogRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserService userService;

    @GetMapping("add")
    public String displayAddDailyLog(Model model) {
        DailyLogTagDTO dailyLogTag = new DailyLogTagDTO();
        model.addAttribute(new DailyLog());
        model.addAttribute("dailyLogTag", dailyLogTag);
        model.addAttribute("tags", tagRepository.findAll());
        return "dailyLog/add";
    }

    @PostMapping("add")
    public String processAddDailyLog(@ModelAttribute @Valid DailyLog newDailyLog,
                                     @ModelAttribute @Valid DailyLogTagDTO dailyLogTag,
                                     Errors errors, Model model, Principal principal,
                                     @AuthenticationPrincipal UserDetails currentUser,
                                     @RequestParam(required = false) Double hoursSlept) {


        if (hoursSlept > 24) {
            model.addAttribute("tooMuch", true);
            return "dailyLog/add";
        }
        
        User user = userRepository.findByUsername(currentUser.getUsername());

        newDailyLog.setUser(user);
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
        DailyLogTagDTO dailyLogTag = new DailyLogTagDTO();
        Optional optDailyLog = dailyLogRepository.findById(id);
        if (optDailyLog.isPresent()) {
            DailyLog dailyLog = (DailyLog) optDailyLog.get();
            model.addAttribute("dailyLog", dailyLog);
            model.addAttribute("dailyLogTag", dailyLogTag);
            model.addAttribute("tags", tagRepository.findAll());
            return "dailyLog/edit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("edit/{id}")
    public String processEditDailyLog(@PathVariable int id, @ModelAttribute DailyLog dailyLog,
                                      @ModelAttribute @Valid DailyLogTagDTO dailyLogTag,
                                      Errors errors, @AuthenticationPrincipal UserDetails currentUser) {

        User user = userRepository.findByUsername(currentUser.getUsername());

        if (!errors.hasErrors()) {
            Tag tag = dailyLogTag.getTag();
            if (!dailyLog.getTags().contains(tag)) {
                dailyLog.addTag(tag);
            }
            dailyLog.setId(id);
            dailyLog.setUser(user);
            dailyLogRepository.save(dailyLog);
        }
        return "dailyLog/view";
    }

    @GetMapping("delete/{id}")
    public String deleteDailyLog(@PathVariable int id) {
        dailyLogRepository.deleteById(id);
        return "index";
    }

    @GetMapping("all")
    public String displayAll(Model model, DailyLog dailyLog,
                             @AuthenticationPrincipal UserDetails currentUser) {

        User user = userRepository.findByUsername(currentUser.getUsername());

        model.addAttribute("dailyLogs", dailyLogRepository.findAllByUserId(Long.valueOf(user.getId())));
        model.addAttribute("january", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-01-01"), Date.valueOf("2021-01-31"), Long.valueOf(user.getId())));
        model.addAttribute("february", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-02-01"), Date.valueOf("2021-02-28"), Long.valueOf(user.getId())));
        model.addAttribute("march", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-03-01"), Date.valueOf("2021-03-31"), Long.valueOf(user.getId())));
        model.addAttribute("april", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-04-01"), Date.valueOf("2021-04-30"), Long.valueOf(user.getId())));
        model.addAttribute("may", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-05-01"), Date.valueOf("2021-05-31"), Long.valueOf(user.getId())));
        model.addAttribute("june", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-06-01"), Date.valueOf("2021-06-30"), Long.valueOf(user.getId())));
        model.addAttribute("july", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-07-01"), Date.valueOf("2021-07-31"), Long.valueOf(user.getId())));
        model.addAttribute("august", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-08-01"), Date.valueOf("2021-08-31"), Long.valueOf(user.getId())));
        model.addAttribute("september", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-09-01"), Date.valueOf("2021-09-30"), Long.valueOf(user.getId())));
        model.addAttribute("october", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-10-01"), Date.valueOf("2021-10-31"), Long.valueOf(user.getId())));
        model.addAttribute("november", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-11-01"), Date.valueOf("2021-11-30"), Long.valueOf(user.getId())));
        model.addAttribute("december", dailyLogRepository.findAllByDateBetweenAndUserIdOrderByDate(Date.valueOf("2021-12-01"), Date.valueOf("2021-12-31"), Long.valueOf(user.getId())));
        return "dailyLog/all";
    }

    @GetMapping("stats")
    public String displaySummary(Model model, @AuthenticationPrincipal UserDetails currentUser) {

        User user = userRepository.findByUsername(currentUser.getUsername());

        Iterable<DailyLog> allLogs = dailyLogRepository.findAllByUserId(Long.valueOf(user.getId()));

        List<DailyLog> currentWeekLogs = new ArrayList<>();
        for (DailyLog log : allLogs) {
            LocalDate localDate = log.getDate().toLocalDate();
            int weekOfYear = localDate.get(WeekFields.of(Locale.ROOT).weekOfYear());
            LocalDate today = LocalDate.now();
            int currentWeekOfYear = today.get(WeekFields.of(Locale.ROOT).weekOfYear());

            if (weekOfYear == currentWeekOfYear) {
                currentWeekLogs.add(log);
            } else {
                continue;
            }
        }

        model.addAttribute("avgMoodOverall", Stats.avgMood(allLogs));
        model.addAttribute("avgMoodCurrentWeek", Stats.avgMood(currentWeekLogs));
        model.addAttribute("avgEnergyOverall", Stats.avgEnergy(allLogs));
        model.addAttribute("avgEnergyCurrentWeek", Stats.avgEnergy(currentWeekLogs));
        model.addAttribute("avgHrsSleepOverall", Stats.avgHoursSlept(allLogs));
        model.addAttribute("avgHrsSleepCurrentWeek", Stats.avgHoursSlept(currentWeekLogs));
        model.addAttribute("avgAlcoholOverall", Stats.avgAlcohol(allLogs));
        model.addAttribute("avgAlcoholCurrentWeek", Stats.avgAlcohol(currentWeekLogs));
        model.addAttribute("avgCaffeineOverall", Stats.avgCaffeine(allLogs));
        model.addAttribute("avgCaffeineCurrentWeek", Stats.avgCaffeine(currentWeekLogs));
        model.addAttribute("daysExercisedOverall", Stats.daysExercised(allLogs));
        model.addAttribute("daysExercisedCurrentWeek", Stats.daysExercised(currentWeekLogs));

        model.addAttribute("maxStreakBreakfast", Stats.maxBreakfastStreak(allLogs));
        model.addAttribute("maxStreakExercise", Stats.maxExerciseStreak(allLogs));
        model.addAttribute("maxStreakAlcohol", Stats.maxStreakLessThanThreeDrinks(allLogs));
        model.addAttribute("maxStreakSleep", Stats.maxStreakSleptOverSevenHours(allLogs));

        model.addAttribute("avgMoodBreakfastTrue", Stats.avgMood(dailyLogRepository.findAllByAteBreakfastTrueAndUserId(Long.valueOf(user.getId()))));
        model.addAttribute("avgEnergyBreakfastTrue", Stats.avgEnergy(dailyLogRepository.findAllByAteBreakfastTrueAndUserId(Long.valueOf(user.getId()))));
        model.addAttribute("avgMoodExerciseTrue", Stats.avgMood(dailyLogRepository.findAllByDidExerciseTrueAndUserId(Long.valueOf(user.getId()))));
        model.addAttribute("avgEnergyExerciseTrue", Stats.avgEnergy(dailyLogRepository.findAllByDidExerciseTrueAndUserId(Long.valueOf(user.getId()))));
        model.addAttribute("avgMoodSleptSeven", Stats.avgMood(dailyLogRepository.findAllByHoursSleptGreaterThanEqualAndUserId(Double.valueOf(7), Long.valueOf(user.getId()))));
        model.addAttribute("avgEnergySleptSeven", Stats.avgEnergy(dailyLogRepository.findAllByHoursSleptGreaterThanEqualAndUserId(Double.valueOf(7), Long.valueOf(user.getId()))));
        model.addAttribute("avgMoodOutsideTrue", Stats.avgMood(dailyLogRepository.findAllByWentOutsideTrueAndUserId(Long.valueOf(user.getId()))));
        model.addAttribute("avgEnergyOutsideTrue", Stats.avgEnergy(dailyLogRepository.findAllByWentOutsideTrueAndUserId(Long.valueOf(user.getId()))));

        return "dailyLog/stats";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer dailyLogId, Model model) {
        Optional<DailyLog> result = dailyLogRepository.findById(dailyLogId);
        DailyLog dailyLog = result.get();
        model.addAttribute("title", "Add Tag to: " + dailyLog.getDate());
        model.addAttribute("tags", tagRepository.findAll());
        DailyLogTagDTO dailyLogTag = new DailyLogTagDTO();
        dailyLogTag.setDailyLog(dailyLog);
        model.addAttribute("dailyLogTag", dailyLogTag);
        return "dailyLog/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid DailyLogTagDTO dailyLogTag, Errors errors,
                                    Model model) {
        if (!errors.hasErrors()) {
            DailyLog dailyLog = dailyLogTag.getDailyLog();
            Tag tag = dailyLogTag.getTag();
            if (!dailyLog.getTags().contains(tag)) {
                dailyLog.addTag(tag);
                dailyLogRepository.save(dailyLog);
            }
            return "redirect:detail?dailyLogId=" + dailyLog.getId();
        }

        return "redirect:add-tag";
    }
}
