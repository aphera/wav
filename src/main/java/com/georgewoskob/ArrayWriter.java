package com.georgewoskob;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayWriter {
    public double[] wavArrayFromFile(File intermediateText) throws IOException {
        List<Double> values = new ArrayList<Double>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(intermediateText));
        String sCurrentLine;
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            try {
                values.add(Double.valueOf(sCurrentLine));
            } catch (NumberFormatException e) {
                //TODO logging
            }
        }
        return listToArray(values);
    }

    public void writeArrayToFile(double[] array, File outputFile) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new FileOutputStream(outputFile));
        for (int i = 0; i < array.length; i++) {
            printStream.print(array[i]);
            printStream.print('\n');
            printStream.flush();
        }
        printStream.close();
    }

    public double[] listToArray(List<Double> values) {
        double[] doubles = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            doubles[i] = values.get(i);
        }
        return doubles;
    }

    public void writeAnalysisToFile(List<double[]> analysis, File file) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new FileOutputStream(file));

        int counter = 0;
        for (double[] doubles : analysis) {
            for (double aDouble : doubles) {
                printStream.print(String.valueOf(aDouble));
                counter += 1;
                printStream.print(' ');
            }
            printStream.print('\n');
            counter += 1;
            printStream.flush();
        }
        printStream.close();
        System.out.println("Number of characters in analysis: " + counter);
    }

    public List<double[]> readAnalysisFromFile(int windowSize, File rawAnalysis) throws IOException {
        List<double[]> analysis = new ArrayList<double[]>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(rawAnalysis));
        String sCurrentLine;

        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            double[] potentialWindow = convertStringToDoubleArray(sCurrentLine);
            analysis.add(Arrays.copyOf(potentialWindow, windowSize));
            //TODO error handling
        }
        System.out.println("Analysis has number of samples: " + analysis.size());
        return analysis;
    }

    private double[] convertStringToDoubleArray(String sCurrentLine) {
        String[] split = sCurrentLine.split(" ");
        double[] potentialWindow = new double[split.length];
        System.out.println("Window has number of buckets: " + potentialWindow.length);
        for (int c = 0; c < split.length; c++) {
            try {
                potentialWindow[c] = Double.valueOf(split[c]);
            } catch (NumberFormatException e) {
                potentialWindow[c] = 0;
            }
        }
        return potentialWindow;
    }
}
