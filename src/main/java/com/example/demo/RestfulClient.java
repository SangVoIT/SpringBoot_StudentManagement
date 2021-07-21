package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Image;
import com.example.demo.utility.Base64ImageUtility;

/***
 * Create REST request Client 
 * @author SANGVO
 *
 */
public class RestfulClient {
	
	final private String urlPattern = "http://localhost:8080/Images/";
	
	RestTemplate restTemplate;
	
	public RestfulClient() {
		restTemplate = new RestTemplate();
		
	}
		
	/* ------------------ */
	/* Setting for POST entity */
	/* ------------------ */
	public void postEntity() {
		System.out.println("1_RestfulClient: Begin /POST request!");
		try {
			String postUrl = urlPattern + "post";
			String name = "demoImage.png";
			String imagePath = "C:\\spring_test\\src\\demoImage.png";
			
			String data = Base64ImageUtility.encoder(imagePath);
			Image imageSending = new Image(name, data);
			ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, imageSending, String.class);
	
			System.out.println("Response for POST request: " + postResponse.getBody());
		} catch (Exception e) {
			System.out.println("1_RestfulClient(postEntity): " + e.toString());
		}
		System.out.println("1_RestfulClient: End /POST request!");
	} 

	/* ------------------ */
	/* Setting for GET entity */
	/* ------------------ */
	public void getEntity() {
		System.out.println("1_RestfulClient: Begin /GET request!");
		try {
			String getUrl = urlPattern + "get?name=demoImage.png";
			
			ResponseEntity<Image> getResponse = restTemplate.getForEntity(getUrl, Image.class);
			
			if (getResponse.getBody() != null) {
				Image image = getResponse.getBody();
				
				System.out.println("Response for GET request:" + image.toString());
				
				// save requested Image to C driver
				System.out.println("Save image to C:\\spring_test\\client");
				
				Base64ImageUtility.decoder(image.getData(), "C:\\spring_test\\client\\" + image.getName());
			}
			
		} catch (Exception e) {
			System.out.println("1_RestfulClient(postEntity): " + e.toString());
		}
		System.out.println("1_RestfulClient: End /GET request!");
	}
	
}
