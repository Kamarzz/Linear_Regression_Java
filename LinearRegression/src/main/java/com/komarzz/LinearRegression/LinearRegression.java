package com.komarzz.LinearRegression;

import org.ejml.simple.SimpleMatrix;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

import java.util.Arrays;

public class LinearRegression {

    private double[] coefficients;

    public void fit(Table XTable, Table yTable) {
        int numInstances = XTable.rowCount();
        int numFeatures = XTable.columnCount();

        double[][] X = new double[numInstances][numFeatures+1];
        double[] y = new double[numInstances];

        for (int i = 0; i < numInstances; i++) {
            X[i][0] = 1;
        }

        for (int i = 0; i < numInstances; i++) {
            for (int j = 0; j < numFeatures; j++) {
                X[i][j+1] = XTable.doubleColumn(j).get(i);
            }
            y[i] = yTable.doubleColumn(0).get(i);
        }

        System.out.println(Arrays.toString(X[0]));

        SimpleMatrix XMatrix = new SimpleMatrix(X);

        SimpleMatrix yMatrix = new SimpleMatrix(y);

        // X^T * X
        SimpleMatrix XT_X = XMatrix.transpose().mult(XMatrix);

        // X^T * y
        SimpleMatrix XT_y = XMatrix.transpose().mult(yMatrix);

        // (X^T * X)^(-1) * X^T * y
        SimpleMatrix coefficientsMatrix;
        coefficientsMatrix = XT_X.invert().mult(XT_y);

        coefficients = coefficientsMatrix.getDDRM().data;
    }





    public Table predict(Table X) {
        DoubleColumn predictions = DoubleColumn.create("Predictions");

        DoubleColumn freechlen = DoubleColumn.create("freechlen");

        for(int i = 0; i < X.rowCount(); i++){
            freechlen.append(1);
        }

        X.insertColumn(0, freechlen);

        for (int rowIndex = 0; rowIndex < X.rowCount(); rowIndex++) {
            double prediction = 0;
            for (int colIndex = 0; colIndex < X.columnCount(); colIndex++) {
                prediction += X.doubleColumn(colIndex).get(rowIndex) * coefficients[colIndex];
            }
            predictions.append(prediction);
        }

        return Table.create("Predictions").addColumns(predictions);
    }
}
