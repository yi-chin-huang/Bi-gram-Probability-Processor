package com.netbaseHw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BiGramProcessingRunnable implements Runnable {
    private final File file;
    public BiGramProcessingRunnable(File file) {
        this.file = file;
    }

    @Override
    public void run()  {
        BiGramResult result = BiGramProcessingRunnable.calculateBiGramProb(file);
        String resultKey = Integer.toString(result.k) + ", " + Double.toString(result.prob);
        SpamFilterV2.modifyResults(resultKey);
    }

    public static BiGramResult calculateBiGramProb(File file) {
        // Load the given file and remove punctuations
        List<String> wordList = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] words = data.split(" ");
                for (String word : words) {
                    String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                    if (!cleanWord.equals(""))
                        wordList.add(cleanWord);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception occurred.");
            e.printStackTrace();
        }

        // Count the number occurrences of each bi-gram
        Map<String, Double> dict = new HashMap<String, Double>();
        for (int i = 1; i < wordList.size(); i++) {
            String key = wordList.get(i - 1) + " " + wordList.get(i);
            if (dict.containsKey(key)) {
                dict.replace(key, dict.get(key) + 1.0);
            } else {
                dict.put(key, 1.0);
            }
        }

        // Calculate the probability
        Iterator it = dict.entrySet().iterator();
        double prob = 1;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            prob *= Math.pow((double) pair.getValue(), (double) pair.getValue());
        }
        int k = wordList.size() - 1;
        double ans = (double) Math.round(Math.pow(prob, 1.0 / k) * 100000d) / 100000d;

        return new BiGramResult(k, ans);
    }
}

final class BiGramResult {
    final int k;
    final double prob;

    public BiGramResult(int k, double prob) {
        this.k = k;
        this.prob = prob;
    }
}