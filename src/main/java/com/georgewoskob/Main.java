package com.georgewoskob;

import com.georgewoskob.wav.WavFileException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static ArrayWriter arrayWriter = new ArrayWriter();

    public static void main(String[] args) throws IOException, WavFileException {
//        testSuite();
        readAnalysisAndWriteToWav();
    }

    private static void testSuite() throws IOException, WavFileException {
        fftIdentity();
        readAnalysisAndWriteToWav();
        wavArrayTransformationIdentity();
    }

    private static void readAnalysisAndWriteToWav() throws IOException, WavFileException {
        int windowSize = 8192;
        List<double[]> analysis = arrayWriter.readAnalysisFromFile(windowSize, new File("/Users/Thoughtworker/leave/wav/src/main/resources/temp.txt"));

        FFT fft = new FFT();
        double[] wav = fft.transformBackward(analysis, windowSize);
        System.out.println("transformed backward");

        WavTransformer wavTransformer = new WavTransformer();
        wavTransformer.writeArrayToWavFile(wav, new File("/Users/Thoughtworker/leave/wav/src/main/resources/temp.wav"));
        System.out.println("wrote wav file");
    }

    private static void fftIdentity() throws IOException, WavFileException {
        WavTransformer wavTransformer = new WavTransformer();
        double[] wavArray = wavTransformer.readWavFileToArray(new File("/Users/Thoughtworker/leave/wav/src/main/resources/prelude.wav"));

        FFT fft = new FFT();

        int windowSize = 8192;
        System.out.println("Window size: " + windowSize);
        List<double[]> analysis = fft.transformForward(wavArray, windowSize);
        System.out.println("transformed forward");

        double[] wav = fft.transformBackward(analysis, windowSize);
        System.out.println("transformed backward");

        wavTransformer.writeArrayToWavFile(wav, new File("/Users/Thoughtworker/leave/wav/src/main/resources/holyFuckThatWorked" + windowSize + ".wav"));
        System.out.println("wrote wav file");

        arrayWriter.writeAnalysisToFile(analysis, new File("/Users/Thoughtworker/leave/wav/src/main/resources/analysis" + windowSize + ".txt"));
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
