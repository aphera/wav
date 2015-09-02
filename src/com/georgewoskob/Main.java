package com.georgewoskob;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, WavFileException {
//        writeWavToText();

        writeTextToWave();
    }

    private static void writeTextToWave() throws IOException, WavFileException {
        List<Integer> values = new ArrayList<Integer>();
        File inputFile = new File("/Users/Thoughtworker/leave/wav/input.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        String sCurrentLine;
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            try {
            values.add(Integer.valueOf(sCurrentLine));
            } catch (NumberFormatException e) {}
        }

        int[] ints = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            ints[i] = values.get(i);
        }
        WavFile ouputWavFile = WavFile.newWavFile(new File("/Users/Thoughtworker/leave/wav/firstMusic.wav"), 1, ints.length, 16, 44100);

        ouputWavFile.writeFrames(ints, ints.length);
        ouputWavFile.display();
        ouputWavFile.close();
    }

    private static void writeWavToText() throws IOException, WavFileException {
        WavFile inputWavFile = WavFile.openWavFile(new File("/Users/Thoughtworker/leave/wav/commodo.wav"));
        inputWavFile.display();
        int numFrames = safeLongToInt(inputWavFile.getNumFrames());
        // Create a buffer of 100 frames
        int[][] buffer = new int[2][numFrames];

        int framesRead;

        framesRead = inputWavFile.readFrames(buffer, (int) numFrames);

        // Close the wavFile
        inputWavFile.close();

        PrintStream printStream = new PrintStream(new FileOutputStream(new File("/Users/Thoughtworker/leave/wav/output.txt")));
        for (int i = 0; i < numFrames; i++) {
            printStream.print(buffer[0][i]);
            printStream.print('\n');
            printStream.flush();
        }
        printStream.close();
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
}
