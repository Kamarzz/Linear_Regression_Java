package com.komarzz.LinearRegression;

import com.komarzz.LinearRegression.Data.DataProcessing;
import com.komarzz.LinearRegression.Data.SplitData;
import tech.tablesaw.api.Table;


import static com.komarzz.LinearRegression.Data.DataProcessing.calculateR2;

public class Main {
    public static void main(String[] args) {
        DataProcessing processor = new DataProcessing("src/main/java/com/komarzz/LinearRegression/" +
                "Data/DataSource/Student_Performance.csv");

        Table data = processor.getData();

        SplitData splitData = DataProcessing.splitData(data, 0.7, 43);

        Table splitDataTrain = splitData.trainingData();
        Table splitDataTest = splitData.testData();

        Table YTrain = Table.create("Performance Index", splitDataTrain.column("Performance Index"));
        Table XTrain = splitDataTrain.removeColumns("Performance Index");
        Table YTest = Table.create("Performance Index", splitDataTest.column("Performance Index"));
        Table XTest = splitDataTest.removeColumns("Performance Index");

        // Обучение линейной регрессии
        LinearRegression lr = new LinearRegression();
        lr.fit(XTrain, YTrain);

        // Предсказание
        Table predictions = lr.predict(XTest);

        // Вывод предсказаний
        double r2 = calculateR2(YTest, predictions);
        System.out.println("Коэффициент детерминации R^2: " + r2);
    }



}
