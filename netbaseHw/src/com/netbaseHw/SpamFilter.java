package com.netbaseHw;

import java.io.File;

public class SpamFilter {

    public static void main(String filePath) {
        File file = new File(filePath);
        BiGramResult result = BiGramProcessingRunnable.calculateBiGramProb(file);
        System.out.println(Double.toString(result.prob) + ", k: " + Integer.toString(result.k));
    }
}
