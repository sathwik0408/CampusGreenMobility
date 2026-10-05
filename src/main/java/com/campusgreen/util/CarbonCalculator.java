package com.campusgreen.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CarbonCalculator {
    private static final double PRIVATE_VEHICLE_FACTOR = 0.192; // kg CO2/km
    private static final double GREEN_MOBILITY_FACTOR = 0.040; // kg CO2/km

    public static double calculateCO2Saved(double distanceKm) {
        double saved = distanceKm * (PRIVATE_VEHICLE_FACTOR - GREEN_MOBILITY_FACTOR);
        BigDecimal bd = new BigDecimal(Double.toString(saved));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
