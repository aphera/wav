package com.georgewoskob;

import java.io.*;
import java.util.ArrayList;
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

        for (double[] doubles : analysis) {
            for (double aDouble : doubles) {
                printStream.print(String.valueOf(aDouble));
                printStream.print(' ');
            }
            printStream.print('\n');
            printStream.flush();
        }
        printStream.close();
    }
}
