package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Image;
import com.example.demo.utility.Base64ImageUtility;

/**
 * SANGVO 20210619 Student Management System
 * @author SANG VO
 */
@RestController
@RequestMapping("/Images")
public class ImagesController {
	
	// -----------------------------------------------------------------------------------
	// GET URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Default get mapping handle
	 * @param model
	 * @return
	 */
	@PostMapping("/post")
	public String postImage(@RequestBody Image image) {
		System.out.println("check");
		System.out.println("ImagesController: postImage; /POST request with " + image.toString());
		System.out.println("check");
		// save Image to C:\\server
		String path = "C:\\spring_test\\server\\" + image.getName();
		Base64ImageUtility.decoder(image.getData(), path);
		
		return "/POST Successful!";
	}

	// -----------------------------------------------------------------------------------
	// POST URL MappingHandle AREA 
	// -----------------------------------------------------------------------------------
	/**
	 * Create a new student to list
	 * @param student: student object from "create_student" view
	 * @return
	 */
	@GetMapping("/get")
	public Image getImage(@RequestParam("name") String name) {
		System.out.println("ImagesController: getImage; /GET info: imageName = " + name);
		
		String imagePath = "C:\\spring_test\\server\\" + name;
		
		String imageBase64 = Base64ImageUtility.encoder(imagePath);
		
		if (imageBase64 != null) {
			Image image = new Image(name, imageBase64);
			return image;
		}
		
		return null;
    }


}
