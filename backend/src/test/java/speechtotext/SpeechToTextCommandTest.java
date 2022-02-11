package speechtotext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.junit.Assert.*;

import com.speechhelper.main.Main;
import com.speechhelper.model.Model;
import com.speechhelper.parsetext.ParseSpeechTextCommand;
import com.speechhelper.speechtotext.GenerateReportCommand;
import com.speechhelper.speechtotext.SpeechToTextCommand;

public class SpeechToTextCommandTest {

	/*@Test
	public void test() {
		Model model = new Model();
		File audioFilePath;
		try {
			audioFilePath = new File(Main.class.getClassLoader().getResource("test.wav").toURI());
			SpeechToTextCommand command = new SpeechToTextCommand(model, audioFilePath);
			command.setSpeech(audioFilePath);
			command.execute();
			assert(command.getSpeech().toString().equals(audioFilePath.toString()));
			assert(command.getText().equals(" this is the first interval speaking  for the first moment of silence is the second of all speaking  so the for the moment of silence is a fucking several speaking and the last "));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
	}*/
	
	@Test
	public void customDictTest() {
		Model model = new Model();
		File audioFilePath;
		File customDict = new File("");
		try {
			audioFilePath = new File(Main.class.getClassLoader().getResource("fillerDemo.wav").toURI());
			String input = "This is sample audio for speech.";
			SpeechToTextCommand command = new SpeechToTextCommand(model, audioFilePath, input);
			command.customizeDictionary();
			customDict = new File("custom.dict");
			assertTrue(customDict.exists());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> words = new ArrayList<String>();
		try(Scanner s = new Scanner(customDict)){
			while(s.hasNext()) {
				String[] line = s.nextLine().split(" ");
				words.add(line[0]);
			}
		}
		catch(FileNotFoundException ex) {
			
		}
		System.out.println(words);
		assertEquals(words.size(), 23);
	}
	
	@Test 
	public void fillerWordTest() {
		Model model = new Model();
		File audioFilePath;
		try {
			audioFilePath = new File(Main.class.getClassLoader().getResource("fillerDemo.wav").toURI());
			String input = "This is a demo for the speaking helper and this demo has a lot of filler words";
			SpeechToTextCommand command = new SpeechToTextCommand(model, audioFilePath, input);
			//command.execute();
			
			//ParseSpeechTextCommand secondCommand = new ParseSpeechTextCommand(model, model.getSpeeches().get(model.getSpeeches().size() - 1));
			//secondCommand.execute();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
