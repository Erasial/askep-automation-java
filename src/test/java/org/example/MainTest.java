package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MainTest {

    @Test
    public void timeResetTest() {
        LocalDate date1 = LocalDate.of(2024, 5, 1);
        LocalDate date2 = LocalDate.of(2024, 5, 5);

        String diffDate = resetTime(date1, date2);

        Assertions.assertEquals("RESET", diffDate);

        String sameDate = resetTime(date1, date1);
        Assertions.assertEquals("NO CHANGES", sameDate);
    }

    private String resetTime(LocalDate date1, LocalDate date2) {
        if (ChronoUnit.DAYS.between(date1, date2) > 0) {
            return "RESET";
        }
        return "NO CHANGES";
    }
}
