package com.speechhelper.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.speechhelper.main.Main;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;

public class GrammarUtility implements SpellCheckListener {

	public static GrammarUtility sharedInstance =  getInstance();
	private SpellChecker checker;
	ArrayList<String> misspelled;

	private static GrammarUtility getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new GrammarUtility();
		}
		return sharedInstance;
	}
	@Override
	public void spellingError(SpellCheckEvent event) {
	    event.ignoreWord(true);
	    misspelled.add(event.getInvalidWord());
		
	}
	
	public HashMap<String, String> evaluate(String s) {
		HashMap<String, String> response = new HashMap<String, String>();
		createDictionary();
	    StringWordTokenizer token = new StringWordTokenizer(s);

	    // how the heck does "misspelled" get populated? through the spellingError method? (possibly)
	    misspelled = new ArrayList<>();

	    checker.addSpellCheckListener(this);
	    checker.checkSpelling(token);

	    Iterator<String> it = misspelled.iterator();
	    while (it.hasNext())
	    {
	      String mistake = it.next();
	      System.out.println("misspelled: " + mistake);
	      System.out.println("Suggestion: " + checker.getSuggestions(mistake, 0).get(0));
	      response.put(mistake, (String)checker.getSuggestions(mistake, 0).get(0));
	    }
	    return response;
	}
	
	
	private void createDictionary()
	  {
		File dict = new File("");
		try {
			dict = new File(Main.class.getClassLoader().getResource("english.0").toURI());
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
	    try
	    {
	      checker = new SpellChecker(new SpellDictionaryHashMap(dict));
	    }
	    catch (FileNotFoundException ex)
	    {
	      ex.printStackTrace();
	    }
	    catch (IOException ex)
	    {
	      ex.printStackTrace();
	    }
	  }

}
