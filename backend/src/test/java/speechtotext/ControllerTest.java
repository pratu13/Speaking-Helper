package speechtotext;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

import com.speechhelper.controller.SpeakingHelperController;
import com.speechhelper.main.Main;
import com.speechhelper.model.Model;
import com.speechhelper.speechtotext.Speech;
import com.speechhelper.speechtotext.SpeechToTextReport;
import com.speechhelper.utilities.FileDownloadUtility;



public class ControllerTest {
	@Test
	public void test() throws MalformedURLException, Exception {
		/*
		try {
			Model model = new Model();
			
			File audioFilePath;
			audioFilePath = new File(Main.class.getClassLoader().getResource("test.wav").toURI());
			Speech speech = new Speech(audioFilePath, "Speechtotext", new SpeechToTextReport());
			model.addSpeech(speech);
			SpeakingHelperController cont = new SpeakingHelperController(model);

			File audioFile2;
			//audioFilePath = new File(Main.class.getClassLoader().getResource("test.wav").toURI());
//			audioFile2 = FileDownloadUtility.getFileFrom(new URL("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-15.mp3"), "audio-test.mp3");
//			cont.createSpeech("https://raw.githubusercontent.com/pratu13/ToDoApp/main/README.md");
//			cont.createSpeech(audioFile2);

			//cont.speechToText(audioFilePath);
			//cont.generateReport(speech);
			//cont.modifySpeech(speech);
			//cont.parseText(speech);
		   
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
