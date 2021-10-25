package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 1, max = 50)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<DailyLog> logs = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DailyLog> getDailyLogs() {
        return logs;
    }
}
