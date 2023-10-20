package com.komarzz.LinearRegression.Data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataProcessing {

    private final String filePath;

    public DataProcessing(String filePath) {
        this.filePath = filePath;
    }

    public List<Student> readDataFromFile() throws IOException, CsvValidationException {
        List<Student> students = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                students.add(new Student(line[0], line[1], line[2], line[3], line[4], line[5]));
            }
        }

        return students;
    }

    public SplitData splitData(List<Student> data, double trainRatio) {
        List<Student> trainingData = new ArrayList<>();
        List<Student> testData = new ArrayList<>();

        Random random = new Random();
        for (Student student : data) {
            if (random.nextDouble() < trainRatio) {
                trainingData.add(student);
            } else {
                testData.add(student);
            }
        }

        return new SplitData(trainingData, testData);
    }

}

