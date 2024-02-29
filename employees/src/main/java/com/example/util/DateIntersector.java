package com.example.util;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;

import com.example.model.EmployeeProject;

public class DateIntersector {
    public static long Intersect(EmployeeProject ep1, EmployeeProject ep2) throws InvalidParameterException {
        LocalDate AStart = ep1.getDateFrom();
        LocalDate AEnd = ep1.getDateTo();
        LocalDate BStart = ep2.getDateFrom();
        LocalDate BEnd = ep2.getDateTo();

        if (AEnd.isBefore(AStart) || BEnd.isBefore(BStart)) {
            throw new InvalidParameterException(
                    "Improper date intervals: [" + AStart + " : " + AEnd + "], [" + BStart + " : " + BEnd + "]");
        } else {
            if (AEnd.isBefore(BStart) || BEnd.isBefore(AStart)) {
                return 0;
            } else {
                LocalDate laterStart = Collections.max(Arrays.asList(AStart, BStart));
                LocalDate earlierEnd = Collections.min(Arrays.asList(AEnd, BEnd));

                // Add 1 to correct for the non-inclusive overlap count
                return ChronoUnit.DAYS.between(laterStart, earlierEnd) + 1;
            }
        }
    };
}
