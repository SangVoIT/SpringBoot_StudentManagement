package com.example.demo.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

public class Base64ImageUtility {
	
	/***
	 * Convert Image path to Base64Image
	 * @param imagePath
	 * @return
	 */
	public static String encoder(String imagePath) {
		File file = new File(imagePath);
		
		try (FileInputStream imageInfile = new FileInputStream(file)){
			// Reading a Image file from file system
			String base64Image = "";
			
			byte imageData[] = new byte[(int) file.length()];
			
			imageInfile.read(imageData);
			
			base64Image = Base64.getEncoder().encodeToString(imageData);
			
			return base64Image;
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	
	/***
	 * Convert Base64Image to Image  
	 * @param base64Image
	 * @param pathFile
	 */
	public static void decoder(String base64Image, String pathFile) {
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)){
			
			// Converting a Base64 String into Image byte Array
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			
			imageOutFile.write(imageByteArray);

		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public static File getImageFromBase64(String base64String, String filePath) {
		
		String[] strings = base64String.split(",");
		String extension = null;
		
		switch (strings[0]) { // check image's extension
		case "data:image/jpeg;base64":
			extension = "jpeg";
			break;
			
		case "data:image/png;base64":
			extension = "png";
			break;
		}
		
		// convert base64 string to binary data
		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		File file = new File(filePath + extension);
		
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))){
			outputStream.write(data);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return file;
	}
	
}
