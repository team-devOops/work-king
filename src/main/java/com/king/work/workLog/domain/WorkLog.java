package com.king.work.workLog.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class WorkLog {
    private UUID userId;
    private LocalDate workingStartDate;
    private LocalDateTime workStartTime;
    private LocalDateTime quittingTime;
    private int officeHours;
    private LocalTime workingTime;

    private WorkLog(UUID userId, LocalDateTime workStartTime, int officeHours) {
        if (userId == null) {
            throw new IllegalArgumentException("userId는 필수입니다.");
        }
        if (officeHours < 0) {
            throw new IllegalArgumentException("근무시간은 0보다 작을 수 없습니다.");
        }

        if (workStartTime == null) {
            throw new IllegalArgumentException("출근시간은 필수입니다.");
        }

        this.userId = userId;
        this.workingStartDate = workStartTime.toLocalDate();
        this.workStartTime = workStartTime;
        this.officeHours = officeHours;
    }

    public static WorkLog startWork(UUID userId, LocalDateTime workStartTime, int officeHours) {
        return new WorkLog(userId, workStartTime, officeHours);
    }

    public void endWork(LocalDateTime quittingTime) {
        this.quittingTime = quittingTime;
        this.workingTime = calcBetweenTime(this.workStartTime, this.quittingTime);
    }

    private LocalTime calcBetweenTime(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return LocalTime.ofSecondOfDay(duration.getSeconds());
    }


}
