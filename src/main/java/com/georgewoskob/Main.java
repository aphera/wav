package com.georgewoskob;

import com.georgewoskob.wav.WavFileException;
import com.georgewoskob.wav.WavTransformer;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, WavFileException {
        identity();
    }

    private static void identity() throws IOException, WavFileException {
        WavTransformer wavTransformer = new WavTransformer();
        ArrayWriter arrayWriter = new ArrayWriter();

        int[] wavArray = wavTransformer.wavToArray(new File("/Users/Thoughtworker/leave/wav/src/main/resources/snares.wav"));

        File intermediateText = new File("/Users/Thoughtworker/leave/wav/intermediate.txt");
        arrayWriter.writeArrayToFile(intermediateText, wavArray);

        int[] newWavArray = arrayWriter.wavArrayFromFile(intermediateText);

        wavTransformer.writeTextToWave(newWavArray, new File("/Users/Thoughtworker/leave/wav/src/main/resources/test.wav"));
        wavTransformer.writeTextToWave(wavArray, new File("/Users/Thoughtworker/leave/wav/src/main/resources/test2.wav"));
    }
}
