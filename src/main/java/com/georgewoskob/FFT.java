package com.georgewoskob;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FFT {
    public List<double[]> transform(double[] input, int windowSize) {

        int counter = 0;

        List<double[]> windowList = new ArrayList<double[]>();

        double[] window = new double[windowSize];
        for (double value : input) {
            if (counter < windowSize) {
                window[counter] = value;
                counter += 1;
            } else {
                counter = 0;
                windowList.add(Arrays.copyOf(window, windowSize));
                window = new double[windowSize];
            }
        }

        List<double[]> fftTransforms = new ArrayList<double[]>();

        for (double[] doubles : windowList) {
            fftTransforms.add(transformWindow(doubles));
        }

        return fftTransforms;
    }

    private double[] transformWindow(double[] window) {
        double[] tempConversion = new double[window.length];

        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        try {
            Complex[] complex = transformer.transform(window, TransformType.FORWARD);

            for (int i = 0; i < complex.length; i++) {
                double rr = (complex[i].getReal());
                double ri = (complex[i].getImaginary());

                tempConversion[i] = Math.sqrt((rr * rr) + (ri * ri));
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        return tempConversion;
    }

}
