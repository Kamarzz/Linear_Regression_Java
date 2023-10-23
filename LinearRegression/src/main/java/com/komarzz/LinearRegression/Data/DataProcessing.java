package com.komarzz.LinearRegression.Data;

import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;

import java.util.Comparator;
import java.util.Random;

public class DataProcessing {

    private Table data;
    private final Table normalizedData = Table.create("Normalized Data");

    public DataProcessing(String filePath) {
            loadData(filePath);
            preprocessData();
            normalizeData();
    }

    private void loadData(String filePath) {
        data = Table.read().csv(filePath);
    }

    private void preprocessData() {
        for (int i = 0; i < data.columnCount(); i++) {
            Column<?> column = data.column(i);
            if (column instanceof StringColumn && column.name().equals("Extracurricular Activities")) {
                DoubleColumn newColumn = DoubleColumn.create(column.name());
                for (String value : (StringColumn) column) {
                    newColumn.append(value.equals("Yes") ? 1.0 : 0.0);
                }
                data.replaceColumn(i, newColumn);
            } else if (column instanceof NumberColumn && !(column instanceof DoubleColumn)) {
                DoubleColumn newColumn = DoubleColumn.create(column.name());
                for (Object value : column) {
                    newColumn.append(((Number) value).doubleValue());
                }
                data.replaceColumn(i, newColumn);
            }
        }

    }
    public Table getData() {
        return data;
    }

    public Table getNormalizedData() {
        return normalizedData;
    }

    public static SplitData splitData(Table table, double proportion, long randomState){
        Comparator<Row> randomComparator = (row1, row2) -> {
            Random random = new Random(randomState);
            return random.nextInt(3) - 1;  // Возвращает -1, 0, или 1 случайным образом
        };
        table.sortOn(randomComparator);
        Table[] splitData = table.sampleSplit(proportion);
        return new SplitData(splitData[0], splitData[1]);
    }

    public void normalizeData() {
        for (int i = 0; i < data.columnCount(); i++) {
            Column<?> column = data.column(i);
            if (column instanceof DoubleColumn doubleColumn) {
                if (column.name().equals("Performance Index")) {
                    normalizedData.addColumns(standardScale(doubleColumn));
                } else {
                    normalizedData.addColumns(minMaxScale(doubleColumn));
                }
            }
        }
    }

    private DoubleColumn standardScale(DoubleColumn column) {

        double mean = column.mean();
        double stdDev = column.standardDeviation();

        DoubleColumn scaledColumn = DoubleColumn.create(column.name());
        for (double value : column) {
            scaledColumn.append((value - mean) / stdDev);
        }

        return scaledColumn;
    }

    private DoubleColumn minMaxScale(DoubleColumn column) {

        double min = column.min();
        double max = column.max();

        DoubleColumn scaledColumn = DoubleColumn.create(column.name());
        for (double value : column) {
            scaledColumn.append((value - min) / (max - min));
        }

        return scaledColumn;
    }


}

