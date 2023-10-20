package com.komarzz.LinearRegression.Data;

import java.util.List;

// Класс для хранения разделенных данных
public class SplitData {
    public final List<Student> trainingData;
    public final List<Student> testData;

    public SplitData(List<Student> trainingData, List<Student> testData) {
        this.trainingData = trainingData;
        this.testData = testData;
    }
}
