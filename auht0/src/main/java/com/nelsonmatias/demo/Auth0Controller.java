package com.nelsonmatias.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import com.nelsonmatias.demo.pojo.Greeting;

@Controller
@SessionAttributes({"userJobTitle", "userAccess"})
/**
 * Auth0 Controller that shows the options for request the protected APIs
 * 
 * @author Nelson.Matias
 *
 */
public class Auth0Controller {
	
	@Value(value = "${api.greeting.url}")
	private String greetingApiUrl;

	@GetMapping("/readGreeting")
	public String auth0(Model model,
			@SessionAttribute("accessToken") String accessToken, @SessionAttribute("nickName") String nickName) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("authorization","Bearer " + accessToken); 

		
			// Create the request body as a MultiValueMap
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

			body.add("name", nickName);

			// Note the body object as first parameter!
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(body, requestHeaders);

			
			System.out.println(greetingApiUrl);
			
			Greeting greeting = restTemplate.exchange(greetingApiUrl,HttpMethod.POST, httpEntity, Greeting.class).getBody();
						
			System.out.println("Greeting is " + greeting);
			

			model.addAttribute("greeting", greeting.getContent());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			model.addAttribute("greeting", "Error code is" + e.getStatusCode());
		} catch (Exception e) {
			model.addAttribute("greeting", "Generic error " + e.getMessage() );
			e.printStackTrace();
		}
		return "auth0";
	}
	
	@PostMapping("/changeGreeting")
	public String writeGreeting(Model model,
			@SessionAttribute("accessToken") String accessToken, @RequestParam("newGreeting") String newGreeting, @SessionAttribute("nickName") String nickName) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("authorization","Bearer " + accessToken); 

		
			System.out.println("Changing the Greeting");
			
			// Create the request body as a MultiValueMap
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

			body.add("name", nickName);
			body.add("newGreeting", newGreeting);
			
			// Note the body object as first parameter!
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(body, requestHeaders);

			Greeting greeting = restTemplate.exchange(greetingApiUrl,HttpMethod.PUT, httpEntity, Greeting.class).getBody();
						
			System.out.println("Greeting is " + greeting);
			

			model.addAttribute("greeting", greeting.getContent());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			model.addAttribute("greeting", "Error code is " + e.getStatusCode());
		} catch (Exception e) {
			model.addAttribute("greeting", "Generic error " + e.getMessage() );
			e.printStackTrace();
		}
		return "auth0";
	}
	
	@GetMapping("/deleteGreeting")
	public String deleteGreeting(Model model,
			@SessionAttribute("accessToken") String accessToken, @SessionAttribute("nickName") String nickName) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("authorization","Bearer " + accessToken); 

			// Create the request body as a MultiValueMap
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     
			body.add("name", nickName);
			// Note the body object as first parameter!
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(body, requestHeaders);

			Greeting greeting = restTemplate.exchange(greetingApiUrl,HttpMethod.DELETE, httpEntity, Greeting.class).getBody();
						
			System.out.println("Greeting is " + greeting);
			

			model.addAttribute("greeting", greeting.getContent());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			model.addAttribute("greeting", "Error code is " + e.getStatusCode());
		} catch (Exception e) {
			model.addAttribute("greeting", "Generic error " + e.getMessage() );
			e.printStackTrace();
		}
		return "auth0";
	}

}
