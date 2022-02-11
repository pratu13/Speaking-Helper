SpeechToText Package
-Contains commands used to interact with the CMU Sphinx Speech to Text API. 
-Also contains custom objects for use in the database, such as Speech, TranscribedSpeechText, and SpeechToText Report.
-Speech objects have a user assigned to them, speech text, audio file, and the transcribed speechtotext given by the API. 
-Objects implement the builder pattern which avoids the need for excessive constructors and makes using the object far easier for the programmer.