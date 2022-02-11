package speechtotext;


import java.io.File;
import java.net.URISyntaxException;

import org.junit.Test;

import com.speechhelper.main.Main;
import com.speechhelper.nullobjects.NullSpeechToTextReport;
import com.speechhelper.speechtotext.Speech;
import com.speechhelper.speechtotext.SpeechToTextReport;

public class SpeechTest {

	@Test
	public void test() {
		File audioFilePath;
		try {
			audioFilePath = new File(Main.class.getClassLoader().getResource("test.wav").toURI());
			Speech speech = new Speech.Builder().speechFile(audioFilePath)
				    .input("Speech to text")
				    .report(new NullSpeechToTextReport())
				    .build();
			assert(speech.getText().equals("Speech to text"));
			assert(speech.getSpeechFile().getPath().toString().equals(audioFilePath.getPath().toString()));
			//assertFalse(speech.toString().equals(speech.toString()));
	
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
