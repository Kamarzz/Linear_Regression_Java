package com.komarzz.LinearRegression.Data;

public class Student {

    private final int HoursStudied;
    private final int PreviousScores;
    private final boolean ExtracurricularActivities;
    private final int SleepHours;
    private final int SampleQuestionPapersPracticed;
    private final float PerformanceIndex;

    public Student(String hoursStudied, String previousScores,
                   String extracurricularActivities, String sleepHours,
                   String sampleQuestionPapersPracticed, String performanceIndex) {

        HoursStudied = parseInteger(hoursStudied);
        PreviousScores = parseInteger(previousScores);
        ExtracurricularActivities = extracurricularActivities.trim().equalsIgnoreCase("Yes");
        SleepHours = parseInteger(sleepHours);
        SampleQuestionPapersPracticed = parseInteger(sampleQuestionPapersPracticed);
        PerformanceIndex = parseFloat(performanceIndex);
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private float parseFloat(String value) {
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

}
