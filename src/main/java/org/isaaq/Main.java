package org.isaaq;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // kalman filter

    public static void main(String[] args) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Distance vs. Time").xAxisTitle("Time").yAxisTitle("Distance").theme(Styler.ChartTheme.GGPlot2).build();
        chart.getStyler().setZoomEnabled(true);
        List<Double> time = new ArrayList<>();
        List<Double> initLine = new ArrayList<>();
        List<Double> filteredLine = new ArrayList<>();

        // Add initial data points
        time.add(0.0);
        initLine.add(0.0);
        filteredLine.add(0.0);
        XYSeries initSeries = chart.addSeries("Init", time, initLine);
        XYSeries filteredSeries = chart.addSeries("Filtered", time, filteredLine);
        initSeries.setSmooth(true);
        filteredSeries.setSmooth(true);

        // use the kalman filter constructor
        // make the prediction 5
        double initvalue = 0;
        double filteredvalue = 0;
        KalmanFilter kalmanFilter = new KalmanFilter(5, 100, 0, 0, 1, 1, KalmanFilter.calculateK(1, 1));
        // create a while true statement and update the kalman filter
        while (true) {
            time.add(time.get(time.size() - 1) + 1);

            // update the kalman filter
            // make the measurement a random number from 1 - 10 and make the number non decimal
            var precision = 1; // 2 decimals
            initvalue = Math.floor(Math.random() * (10 * precision - precision) + precision) / (precision);
            // print the kalman filtered values

            filteredvalue = kalmanFilter.update(initvalue);
            initLine.add(initvalue);
            filteredLine.add(filteredvalue);
            chart.updateXYSeries("Init", time, initLine, null);
            chart.updateXYSeries("Filtered", time, filteredLine, null);
            // print the kalman filter values
            System.out.println("x: " + kalmanFilter.getX());
            System.out.println("p: " + kalmanFilter.getP());
            System.out.println("u: " + kalmanFilter.getU());
            System.out.println("z: " + kalmanFilter.getZ());
            System.out.println("Q: " + kalmanFilter.getQ());
            System.out.println("R: " + kalmanFilter.getR());
            System.out.println("K: " + kalmanFilter.getK());
            System.out.println("x_previous: " + kalmanFilter.getX_previous());
            System.out.println("p_previous: " + kalmanFilter.getP_previous());
            System.out.println("initvalue: " + initvalue);
            System.out.println("filteredvalue: " + filteredvalue);
            // graph filtered value and initvalue
            new SwingWrapper<>(chart).displayChart();
            try {
                Thread.sleep(1000); // Delay for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}