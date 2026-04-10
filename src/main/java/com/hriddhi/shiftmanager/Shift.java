package com.hriddhi.shiftmanager;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }



    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;



    @NotBlank(message = "Start time is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be HH:mm")
    private String startTime;
    @NotBlank(message = "End time is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be HH:mm")
    private String endTime;
    private String date;
    public String getDate(){
        return date;
    }// format: YYYY-MM-DD
}
