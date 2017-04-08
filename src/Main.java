import lab1.*;
import lab2.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        new Thread(() -> {
            int arraySize = new Scanner(System.in).nextInt();
            int[] array = new int[0];
            try {
                array = SecureRandom.getInstance("SHA1PRNG").ints(arraySize).toArray();
                Files.write(Paths.get("input.txt"), Arrays.toString(array).getBytes());
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }

            int number = new Scanner(System.in).nextInt();
            System.out.println("The " + number + "th Minimum: " + QuickSort.findNthMinimum(array, number));
            QuickSort.sort(array);

            int width = (int) Math.log10(arraySize) + 2;
            for (int i = 0; i < arraySize; i++) {
                System.out.format("%" + width + "d %11d ", i + 1, array[i]);
                if ((i + 1) % 10 == 0) {
                    System.out.print("\n");
                }
            }
        }).start();

        String fontName = "PingFangSC";

        CategoryDataset categoryDataset = getDataSet();
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Different Algorithms Consume Time Comparison Graphs",
                "Data Size(Million)",
                "Time Consumed(Millisecond)",
                categoryDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        lineChart.setAntiAlias(true);
        lineChart.setTextAntiAlias(true);

        CategoryPlot categoryPlot = (CategoryPlot) lineChart.getPlot();
        categoryPlot.setDomainGridlinesVisible(false);
        categoryPlot.setRangeGridlinesVisible(false);
        categoryPlot.setBackgroundPaint(Color.white);
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        TextTitle textTitle = lineChart.getTitle();
        textTitle.setFont(new Font(fontName, Font.PLAIN, 32));
        categoryAxis.setTickLabelFont(new Font(fontName, Font.PLAIN, 20));
        categoryAxis.setLabelFont(new Font(fontName, Font.PLAIN, 20));
        numberAxis.setTickLabelFont(new Font(fontName, Font.PLAIN, 20));
        numberAxis.setLabelFont(new Font(fontName, Font.PLAIN, 20));
        lineChart.getLegend().setItemFont(new Font(fontName, Font.PLAIN, 20));

        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
        lineandshaperenderer.setBaseShapesVisible(true);
        lineandshaperenderer.setBaseLinesVisible(true);
        lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        lineandshaperenderer.setBaseItemLabelsVisible(true);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("chart.jpg");
            ChartUtilities.writeChartAsJPEG(fileOutputStream, 1.0f, lineChart, 2000, 1600, null);
        } finally {
            try {
                assert fileOutputStream != null;
                fileOutputStream.close();
            } catch (Exception ignored) {
            }
        }
    }

    private static CategoryDataset getDataSet() throws NoSuchAlgorithmException {
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();

        for (int arraySize = 1_000_000; arraySize <= 15_000_000; arraySize += 1_000_000) {
            int[] array = SecureRandom.getInstance("SHA1PRNG").ints(arraySize).toArray();
            int[] arrayCopy1 = Arrays.copyOf(array, array.length);
//            int[] arrayCopy2 = Arrays.copyOf(array, array.length);

            long startTime = System.currentTimeMillis();
            MergeSort.sort(array);
            long timeDifference = System.currentTimeMillis() - startTime;
            categoryDataset.addValue(timeDifference, "Merge Sort", arraySize / 1_000_000 + "M");
            startTime = System.currentTimeMillis();
            QuickSort.sort(arrayCopy1);
            timeDifference = System.currentTimeMillis() - startTime;
            categoryDataset.addValue(timeDifference, "Quick Sort", arraySize / 1_000_000 + "M");
//            startTime = System.currentTimeMillis();
//            InsertionSort.sort(arrayCopy2);
//            timeDifference = System.currentTimeMillis() - startTime;
//            categoryDataset.addValue(timeDifference, "Insertion Sort", arraySize / 10000 + "W");
        }

        return categoryDataset;
    }
}
