package com.netbaseHw;

public class Main {

    public static void main(String[] args) {
        // Problem 1 - The Spam Filter
        System.out.println("Output of problem 1: ");
        SpamFilter.calculateBiGramProb("src/com/netbaseHw/sampleDirectory/SampleData.txt");

        // Problem 2 - The Spam Filter v2
        System.out.println("Output of problem 2: ");
        SpamFilterV2.calculateBiGramProb("src/com/netbaseHw/sampleDirectory");
    }
}
