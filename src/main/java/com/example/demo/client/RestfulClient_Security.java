package com.example.demo.client;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Image;
import com.example.demo.entity.SignupForm;

import Interceptor.LoggingInterceptor;
import lombok.extern.slf4j.Slf4j;

/***
 * Create REST request Client 
 * @author SANGVO
 *
 */
@Slf4j
public class RestfulClient_Security {
	
	final private String urlPattern = "http://localhost:8080/";
	
	RestTemplate restTemplate;
	
	public RestfulClient_Security() {
		
		ClientHttpRequestFactory factory = 
		        new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		
		restTemplate = new RestTemplate(factory);
		
		// Setting interceptor for RestFulClient
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
		    interceptors = new ArrayList<>();
		}
		
		interceptors.add(new LoggingInterceptor());
		restTemplate.setInterceptors(interceptors);
		
	}

	/**
	 * Signup URL testing
	 */
	public void postEntity_2() {
		log.info("RestfulClient_Security: Begin /POST request!");
		try {
			
			// Request address
			String url = "http://localhost:8080/signup";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			map.add("email", "sangvo.it@gmail.com");
			map.add("name", "sangvo");
			map.add("password", "sa12345.");

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class);
			
		} catch (Exception e) {
			log.info("RestfulClient_Security(postEntity): " + e.toString());
		}
		log.info("RestfulClient_Security: End /POST request!");
	} 
	

	/**
	 * Signup URL testing
	 */
	public void postEntity_1() {
		log.info("RestfulClient_Security: Begin /POST request!");
		try {
			// URL Setting
			String postUrl = urlPattern + "signup";

		    // create request body
		    JSONObject requestJson = new JSONObject();
		    requestJson.put("email", "sangvo.it@gmail.com");
		    requestJson.put("name", "sangvo");
		    requestJson.put("password", "sa12345.");
		    
			// Header Setting
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    HttpEntity<String> request = 
		      new HttpEntity<String>(requestJson.toString(), headers);
		    
		    String postResponse = restTemplate.postForObject(postUrl, request, String.class);
	
			log.info("Response for POST request: " + postResponse);
		} catch (Exception e) {
			log.info("RestfulClient_Security(postEntity): " + e.toString());
		}
		log.info("RestfulClient_Security: End /POST request!");
	} 
	

	/**
	 * Signup URL testing
	 */
	public void postEntity() {
		log.info("RestfulClient_Security: Begin /POST request!");
		try {
			// URL Setting
			String postUrl = urlPattern + "signup";

		    // create request body
			SignupForm signupForm = new SignupForm();
			signupForm.setEmail("sangvo.it@gmail.com");
			signupForm.setName("sangvo");
			signupForm.setPassword("sa12345.");
		    
		    String postResponse = restTemplate.postForObject(postUrl, signupForm, String.class);
	
			log.info("Response for POST request: " + postResponse);
			
		} catch (Exception e) {
			log.info("RestfulClient_Security(postEntity): " + e.toString());
		}
		log.info("RestfulClient_Security: End /POST request!");
	} 

	public void getEntity() {
		log.info("RestfulClient_Security: Begin /GET request!");
		try {
			String getUrl = urlPattern;
			
			ResponseEntity<Image> getResponse = restTemplate.getForEntity(getUrl, Image.class);
			
			if (getResponse.getBody() != null) {

				log.info("Response for GET request: " + getResponse.getBody());
			}
			
		} catch (Exception e) {
			log.info("RestfulClient_Security(postEntity): " + e.toString());
		}
		log.info("RestfulClient_Security: End /GET request!");
	}
	
}
