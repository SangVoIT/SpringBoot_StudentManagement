package com.example.demo.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

/***
 * Image Upload Utility
 * @author SANGVO
 *
 */
public class FileUploadUtility {
	
	/***
	 * Upload Client image to Server
	 * @param uploadDir
	 * @param fileName
	 * @param multipartFile
	 * @throws IOException
	 */
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		// Create upload folder if not existing
		if (!Files.exists(uploadPath)){
			Files.createDirectories(uploadPath);
		}
		
		// Upload multipartFile to uploadDir + filename
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}  
	}
	
	
	
}
