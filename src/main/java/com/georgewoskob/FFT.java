package com.georgewoskob;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class FFT {
    private double[] transform(double[] input)
    {

        double[] tempConversion = new double[input.length];

        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        try {
            Complex[] complex = transformer.transform(input, TransformType.FORWARD);

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
