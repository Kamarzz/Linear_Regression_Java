package com.komarzz.LinearRegression;

import com.komarzz.LinearRegression.Data.DataProcessing;
import com.komarzz.LinearRegression.Data.SplitData;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class Main {
    public static void main(String[] args) {
        DataProcessing processor = new DataProcessing("src/main/java/com/komarzz/LinearRegression/" +
                "Data/DataSource/Student_Performance.csv");

        Table normalizedData = processor.getNormalizedData();

        Table Y = Table.create("Performance Index", normalizedData.column("Performance Index"));
        Table X = normalizedData.removeColumns("Performance Index");

        SplitData YSplit = DataProcessing.splitData(Y, 0.7, 43);
        SplitData XSplit = DataProcessing.splitData(X, 0.7, 43);

        Table YTrain = YSplit.trainingData();
        Table YTest = YSplit.testData();
        Table XTrain = XSplit.trainingData();
        Table XTest = XSplit.testData();

        // Обучение линейной регрессии
        LinearRegression lr = new LinearRegression();
        lr.fit(XTrain, YTrain);

        // Предсказание
        Table predictions = lr.predict(XTest);

        // Вывод предсказаний
        double r2 = calculateR2(YTest, predictions);
        System.out.println("Коэффициент детерминации R^2: " + r2);
    }



    public static double calculateR2(Table yTrue, Table yPred) {
        if (yTrue.rowCount() != yPred.rowCount()) {
            throw new IllegalArgumentException("The number of rows in yTrue and yPred must be the same.");
        }
        DoubleColumn trueColumn = yTrue.doubleColumn(0);
        DoubleColumn predColumn = yPred.doubleColumn(0);
        double meanY = trueColumn.mean();
        double rss = 0;
        double tss = 0;
        for (int i = 0; i < trueColumn.size(); i++) {
            double tY = trueColumn.get(i);
            double pY = predColumn.get(i);
            rss += Math.pow(tY - pY, 2);
            tss += Math.pow(tY - meanY, 2);
        }
        return 1 - (rss / tss);
    }


}
