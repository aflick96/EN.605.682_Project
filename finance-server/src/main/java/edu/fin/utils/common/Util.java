package edu.fin.utils.common;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Util {
    public LocalDate getFirstOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    public LocalDate getLastOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth());
    }
}
