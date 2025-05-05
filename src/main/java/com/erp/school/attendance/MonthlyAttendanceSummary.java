package com.erp.school.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyAttendanceSummary {
    private int presentDays;
    private int absentDays;
    private int leaveDays;
    private int holidays;
}