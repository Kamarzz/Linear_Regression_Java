package com.komarzz.LinearRegression;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import tech.tablesaw.api.Table;

import javax.swing.*;
import java.awt.*;

public class VisualizationUtility {


    private VisualizationUtility(){}

    public static void printTable(Table table){
        System.out.println(table.print());
    }

    public static void showHistogram(double[] data, String title) {

        int binCount = (int) (1 + 3.322 * Math.log10(data.length));

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries(title, data, binCount);

        JFreeChart histogram = ChartFactory.createHistogram(
                title,
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(histogram);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

}
