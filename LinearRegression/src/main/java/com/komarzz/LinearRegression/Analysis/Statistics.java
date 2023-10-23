package com.komarzz.LinearRegression.Analysis;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statistics {

    Table data;
    private final Table statistic = Table.create("Statistic");


    public Statistics(Table data) {
        this.data = data;

        statistic.addColumns(createNamingColumn());

        for (int i = 0; i < data.columnCount(); i++) {
            statistic.addColumns(createColumnStatistic(data.doubleColumn(i)));
        }
    }

    private StringColumn createNamingColumn() {
        StringColumn names = StringColumn.create("Summary");
        names.append("Count");
        names.append("Mean");
        names.append("Deviation");
        names.append("Min");
        names.append("Max");
        names.append("Quantile1");
        names.append("Quantile2");
        names.append("Quantile3");
        return names;
    }

    private DoubleColumn createColumnStatistic(DoubleColumn column) {
        DoubleColumn res = DoubleColumn.create(column.name());
        res.append(calculateCount(column));
        res.append(calculateMean(column));
        res.append(calculateStandardDeviation(column));
        res.append(calculateMin(column));
        res.append(calculateMax(column));
        res.append(calculateQuantile(column, 0.25));
        res.append(calculateQuantile(column, 0.5));
        res.append(calculateQuantile(column, 0.75));
        return res;
    }

    private double calculateCount(DoubleColumn column) {
        return column.size();
    }

    private double calculateMean(DoubleColumn column) {
        double sum = 0;
        for (double element : column) {
            sum += element;
        }
        return sum / column.size();
    }

    private double calculateStandardDeviation(DoubleColumn column) {
        double mean = calculateMean(column);
        double sum = 0;
        for (double element : column) {
            sum += Math.pow(element - mean, 2);
        }
        return Math.sqrt(sum / column.size());
    }

    private double calculateMin(DoubleColumn column) {
        List<Double> values = new ArrayList<>();
        for (double value : column) {
            values.add(value);
        }
        return Collections.min(values);
    }

    private double calculateMax(DoubleColumn column) {
        List<Double> values = new ArrayList<>();
        for (double value : column) {
            values.add(value);
        }
        return Collections.max(values);
    }

    private double calculateQuantile(DoubleColumn column, double quantile) {
        List<Double> values = new ArrayList<>();
        for (double value : column) {
            values.add(value);
        }
        Collections.sort(values);
        int index = (int) (quantile * values.size());
        return values.get(index);
    }

    public Table getStatisticTable() {
        return statistic;
    }
}

