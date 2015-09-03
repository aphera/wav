package com.georgewoskob;

import com.georgewoskob.wav.WavFileException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static ArrayWriter arrayWriter = new ArrayWriter();

    public static void main(String[] args) throws IOException, WavFileException {
        WavTransformer wavTransformer = new WavTransformer();
        double[] wavArray = wavTransformer.wavToArray(new File("/Users/Thoughtworker/leave/wav/src/main/resources/snares.wav"));

        FFT fft = new FFT();
        List<double[]> analysis = fft.transform(wavArray, 1024);

        arrayWriter.writeAnalysisToFile(analysis, new File("/Users/Thoughtworker/leave/wav/src/main/resources/analysis.txt"));
        int expectedNumberOfTransforms = wavArray.length / 1024;
        System.out.println("Number of expected transforms: " + expectedNumberOfTransforms);
        System.out.println("Number of transforms: " + analysis.size());
    }

    private static void identity() throws IOException, WavFileException {
        WavTransformer wavTransformer = new WavTransformer();
        ArrayWriter arrayWriter = new ArrayWriter();

        double[] wavArray = wavTransformer.wavToArray(new File("/Users/Thoughtworker/leave/wav/src/main/resources/snares.wav"));

        File intermediateText = new File("/Users/Thoughtworker/leave/wav/intermediate.txt");
        arrayWriter.writeArrayToFile(wavArray, intermediateText);

        double[] newWavArray = arrayWriter.wavArrayFromFile(intermediateText);

        wavTransformer.writeTextToWave(newWavArray, new File("/Users/Thoughtworker/leave/wav/src/main/resources/test.wav"));
        wavTransformer.writeTextToWave(wavArray, new File("/Users/Thoughtworker/leave/wav/src/main/resources/test2.wav"));
    }
}
