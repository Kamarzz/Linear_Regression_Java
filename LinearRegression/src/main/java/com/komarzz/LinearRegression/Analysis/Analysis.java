package com.komarzz.LinearRegression.Analysis;

import com.komarzz.LinearRegression.Data.DataProcessing;
import com.komarzz.LinearRegression.VisualizationUtility;
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;


public class Analysis {

    public static void main(String[] args) {

        DataProcessing processor = new DataProcessing("src/main/java/com/komarzz/LinearRegression/" +
                "Data/DataSource/Student_Performance.csv");
        Table data = processor.getData();

        for(int i = 0; i < data.columnCount(); i++){
            if (data.column(i) instanceof NumericColumn) {
                double[] arr = new double[data.column(i).size()];

                for (int j = 0; j < arr.length; j++) {
                    arr[j] = ((NumericColumn<?>) data.column(i)).getDouble(j);
                }

                VisualizationUtility.showHistogram(arr, data.column(i).name());
            }
        }

        Statistics stat = new Statistics(data);
        VisualizationUtility.printTable(stat.getStatisticTable());
    }


}

