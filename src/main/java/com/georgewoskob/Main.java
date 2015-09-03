package com.georgewoskob;

import com.georgewoskob.wav.WavFileException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static ArrayWriter arrayWriter = new ArrayWriter();

    public static void main(String[] args) throws IOException, WavFileException {
        fftIdentity();
        wavArrayTransformationIdentity();
    }

    private static void fftIdentity() throws IOException, WavFileException {
        WavTransformer wavTransformer = new WavTransformer();
        double[] wavArray = wavTransformer.readWavFileToArray(new File("/Users/Thoughtworker/leave/wav/src/main/resources/snares.wav"));

        FFT fft = new FFT();

        int windowSize = 1024;
        List<double[]> analysis = fft.transformForward(wavArray, windowSize);

        double[] wav = fft.transformBackward(analysis, windowSize);
        wavTransformer.writeArrayToWavFile(wav, new File("/Users/Thoughtworker/leave/wav/src/main/resources/holyFuckThatWorked.wav"));

        arrayWriter.writeAnalysisToFile(analysis, new File("/Users/Thoughtworker/leave/wav/src/main/resources/analysis.txt"));
        int expectedNumberOfTransforms = wavArray.length / windowSize;
        System.out.println("Number of expected transforms: " + expectedNumberOfTransforms);
        System.out.println("Number of transforms: " + analysis.size());
    }

    private static void wavArrayTransformationIdentity() throws IOException, WavFileException {
        WavTransformer wavTransformer = new WavTransformer();
        ArrayWriter arrayWriter = new ArrayWriter();

        double[] wavArray = wavTransformer.readWavFileToArray(new File("/Users/Thoughtworker/leave/wav/src/main/resources/snares.wav"));

        File intermediateText = new File("/Users/Thoughtworker/leave/wav/intermediate.txt");
        arrayWriter.writeArrayToFile(wavArray, intermediateText);

        double[] newWavArray = arrayWriter.wavArrayFromFile(intermediateText);

        wavTransformer.writeArrayToWavFile(newWavArray, new File("/Users/Thoughtworker/leave/wav/src/main/resources/test.wav"));
        wavTransformer.writeArrayToWavFile(wavArray, new File("/Users/Thoughtworker/leave/wav/src/main/resources/test2.wav"));
    }
}
