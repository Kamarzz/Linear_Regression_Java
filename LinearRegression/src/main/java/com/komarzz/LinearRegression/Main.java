package com.komarzz.LinearRegression;

import com.komarzz.LinearRegression.Data.DataProcessing;
import com.komarzz.LinearRegression.Data.SplitData;
import com.komarzz.LinearRegression.Data.Student;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

        List<Student> data;

        DataProcessing processor = new DataProcessing("src/main/java/com/komarzz/LinearRegression/Data" +
                "/DataSource/Student_Performance.csv");

        data = processor.readDataFromFile();

        SplitData splitData = processor.splitData(data, 0.8);


        List<Student> trainingData = splitData.trainingData;
        List<Student> testData = splitData.testData;


    }
}