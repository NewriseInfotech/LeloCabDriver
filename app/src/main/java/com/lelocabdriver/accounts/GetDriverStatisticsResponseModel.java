package com.lelocabdriver.accounts;

import com.lelocabdriver.utils.Util;

import java.io.Serializable;

/**
 * Created by ashish on 16-05-2017.
 */

public class GetDriverStatisticsResponseModel implements Serializable {
    private String CurrentMonthIncome;
    private String TodaysIncome;
    private String CurrentMonthLoginHours;
    private String TodaysRideHours;
    private String TodaysLoginHours;
    private String CurrentMonthRideHours;
    private String CurrentMonthDistance;
    private String TodaysDistance;

    public String getCurrentMonthIncome() {
        return Util.getDigits(Double.parseDouble(CurrentMonthIncome));
    }

    public void setCurrentMonthIncome(String currentMonthIncome) {
        CurrentMonthIncome = currentMonthIncome;
    }

    public String getTodaysIncome() {
        return Util.getDigits(Double.parseDouble(TodaysIncome));
    }

    public void setTodaysIncome(String todaysIncome) {
        TodaysIncome = todaysIncome;
    }

    public String getCurrentMonthLoginHours() {
        return CurrentMonthLoginHours;
    }

    public void setCurrentMonthLoginHours(String currentMonthLoginHours) {
        CurrentMonthLoginHours = currentMonthLoginHours;
    }

    public String getTodaysRideHours() {
        return TodaysRideHours;
    }

    public void setTodaysRideHours(String todaysRideHours) {
        TodaysRideHours = todaysRideHours;
    }

    public String getTodaysLoginHours() {
        return TodaysLoginHours;
    }

    public void setTodaysLoginHours(String todaysLoginHours) {
        TodaysLoginHours = todaysLoginHours;
    }

    public String getCurrentMonthRideHours() {
        return CurrentMonthRideHours;
    }

    public void setCurrentMonthRideHours(String currentMonthRideHours) {
        CurrentMonthRideHours = currentMonthRideHours;
    }

    public String getCurrentMonthDistance() {
        return Util.getDigits(Double.parseDouble(CurrentMonthDistance));
    }

    public void setCurrentMonthDistance(String currentMonthDistance) {
        CurrentMonthDistance = currentMonthDistance;
    }

    public String getTodaysDistance() {
        return Util.getDigits(Double.parseDouble(TodaysDistance));
    }

    public void setTodaysDistance(String todaysDistance) {
        TodaysDistance = todaysDistance;
    }
}
