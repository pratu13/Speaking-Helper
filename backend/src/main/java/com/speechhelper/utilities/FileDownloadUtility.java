package com.speechhelper.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.speechhelper.main.Main;

/// Given a url and fileName, download the contents of the URL and save it to a file.
public class FileDownloadUtility {

	private static void downloadFile(URL url, String outputPath) throws Exception {
		InputStream inputStream = url.openStream();
		Files.copy(inputStream, Paths.get(outputPath), StandardCopyOption.REPLACE_EXISTING);
	}
	
	// returns the contents of a text file as a string
	public static String getFileContents(URL url, String fileName) throws Exception {
		String outputPath = "src/main/resources/" + fileName;
		downloadFile(url, outputPath);
		String fileContent = "";
		try {
			fileContent = new String(Files.readAllBytes(Paths.get(outputPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}
	
	// returns the file after downloading it 
	public static File getFileFrom(URL url, String fileName) throws Exception {
		String outputPath = "src/main/resources/" + fileName;
		downloadFile(url, outputPath);
		File file; 
		try {
			file = new File(Main.class.getClassLoader().getResource(fileName).toURI());
			return file;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new File(Main.class.getClassLoader().getResource("test.wav").toURI());
		
	}

}
