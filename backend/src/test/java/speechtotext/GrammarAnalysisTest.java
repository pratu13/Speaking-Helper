package speechtotext;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.speechhelper.model.Model;
import com.speechhelper.speechtotext.Speech;

class GrammarAnalysisTest {

	@Test
	void test() {
		Model m = new Model();
		Speech testSpeech = new Speech.Builder().input("This is some samlpe mispelled tetx fo the analysis").build();
		//GrammarAnalysisCommand grammar = new GrammarAnalysisCommand(m, testSpeech);
		//grammar.execute();
	}

}
