package com.speechhelper.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FillerWordsUtility {

	public static FillerWordsUtility sharedInstance = getInstance();

	private HashMap<String, Integer> fillerFreq = new HashMap<>();
	private ArrayList<String> fillerWords;

	public FillerWordsUtility() {
		fillerWords = new ArrayList<String>();
		String path = getClass().getClassLoader().getResource("fillerWords.txt").getPath();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] fillers = line.split(",");
				for (String filler : fillers) {
					fillerWords.add(filler);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("File read issue");
		}
	}

	private static FillerWordsUtility getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new FillerWordsUtility();
		}
		return sharedInstance;
	}

	public HashMap<String, Integer> getFillersFrequency(HashMap<String, Integer> wordFrequency) {

		HashMap<String, Integer> fillers = new HashMap<String, Integer>();
		for (String filler : fillerWords) {
			if(wordFrequency.containsKey(filler)) {
				fillerFreq.put(filler, wordFrequency.get(filler));
			}
			if (wordFrequency.containsKey(filler) && wordFrequency.get(filler) > 5) {
				fillers.put(filler, wordFrequency.get(filler));
			}
		}
		return fillers;
	}

	public int getFillerWordsRatio(Integer totalWords) {
		Integer totalFillerWords = 0;
		for (int i = 0; i < fillerFreq.values().size(); i++) {
			int n = (Integer) fillerFreq.values().toArray()[i];
			totalFillerWords += n;
		}
		return (int)((totalFillerWords/totalWords)  * 100);
	}
}
