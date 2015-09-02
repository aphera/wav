package com.georgewoskob;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayWriter {
    public int[] wavArrayFromFile(File intermediateText) throws IOException {
        List<Integer> values = new ArrayList<Integer>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(intermediateText));
        String sCurrentLine;
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            try {
                values.add(Integer.valueOf(sCurrentLine));
            } catch (NumberFormatException e) {
                //TODO logging
            }
        }
        return listToArray(values);
    }

    public void writeArrayToFile(File outputFile, int[] array) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new FileOutputStream(outputFile));
        for (int i = 0; i < array.length; i++) {
            printStream.print(array[i]);
            printStream.print('\n');
            printStream.flush();
        }
        printStream.close();
    }

    public int[] listToArray(List<Integer> values) {
        int[] ints = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            ints[i] = values.get(i);
        }
        return ints;
    }
}
