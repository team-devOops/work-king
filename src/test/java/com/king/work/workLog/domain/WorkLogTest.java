package com.king.work.workLog.domain;


import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import static org.assertj.core.api.SoftAssertions.assertSoftly;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkLogTest {

    @Test
    @DisplayName("출근 기록이 등록된다.")
    void createWorkLog() {
        // given
        LocalDateTime workStartTime = LocalDateTime.of(2021, 1, 1, 9, 0);
        WorkLog workLog = WorkLog.startWork(UUID.randomUUID(), workStartTime, 8);

        //when & then
        assertSoftly(
            softly -> {
                softly.assertThat(workLog.getUserId()).isNotNull();
                softly.assertThat(workLog.getWorkingStartDate()).isEqualTo(workStartTime.toLocalDate());
                softly.assertThat(workLog.getWorkStartTime()).isEqualTo(workStartTime);
                softly.assertThat(workLog.getOfficeHours()).isEqualTo(8);
                softly.assertThat(workLog.getQuittingTime()).isNull();
                softly.assertThat(workLog.getWorkingTime()).isNull();
            }
        );
    }

    @Test
    @DisplayName("퇴근 기록을 등록할때 userId는 필수이다.")
    void createWorkLogWithoutUserId() {
        // given
        LocalDateTime workStartTime = LocalDateTime.of(2021, 1, 1, 9, 0);

        //when & then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WorkLog.startWork(null, workStartTime, 8);
        });
    }

    @Test
    @DisplayName("퇴근 기록을 등록할때 officeHours는 0보다 작을 수 없다.")
    void createWorkLogWithNegativeOfficeHours() {
        // given
        LocalDateTime workStartTime = LocalDateTime.of(2021, 1, 1, 9, 0);

        //when & then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WorkLog.startWork(UUID.randomUUID(), workStartTime, -1);
        });
    }

    @Test
    @DisplayName("퇴근 기록을 등록할때 workStartTime은 필수이다.")
    void createWorkLogWithoutWorkStartTime() {
        // given
        LocalDateTime workStartTime = LocalDateTime.of(2021, 1, 1, 9, 0);

        //when & then
        assertThatIllegalArgumentException().isThrownBy(() -> WorkLog.startWork(UUID.randomUUID(), null, 8));
    }

    @Test
    @DisplayName("퇴근 기록이 등록 된다.")
    void endWorkLog() {
        // given
        LocalDateTime workStartTime = LocalDateTime.of(2021, 1, 1, 9, 0);
        LocalDateTime workEndTime = LocalDateTime.of(2021, 1, 1, 18, 1);
        WorkLog workLog = WorkLog.startWork(UUID.randomUUID(), workStartTime, 8);

        //when
        workLog.endWork(workEndTime);

        //then
        assertSoftly(
            softly -> {
                softly.assertThat(workLog.getQuittingTime()).isEqualTo(workEndTime);
                softly.assertThat(workLog.getWorkingTime()).isEqualTo(LocalTime.of(9, 1));
            }
        );
    }


}