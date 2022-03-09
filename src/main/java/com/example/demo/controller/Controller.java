package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.News;
import com.example.demo.model.SpecNews;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@org.springframework.stereotype.Controller
public class Controller {
	
	String category = "";
	
	@RequestMapping(value = {"","/", "/home"})
	public String onestep(Model model) throws IOException {
		
		String uri = "https://newsapi.org/v2/top-headlines?country=us&apiKey=5b0473d941044694a5c0e6452934bcc1";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		
		/*String result = new String(Files.readAllBytes(Paths.get("src/main/resources/sample.json"))); 
		*/
		System.out.println(result);
		ObjectMapper objectMapper = new ObjectMapper();
		News news1 = objectMapper.readValue(result, News.class); 
		System.out.println(news1.getStatus());
		//System.out.println("Hii: " + news1.getArticles().get(0).);
		
		model.addAttribute("articles", news1.getArticles());
		
		System.out.println(news1);
		
		return "index.html";
	}
	
	@PostMapping(value = "/search")
	public String twostep(Model model, @RequestParam("searchvalue") String value) throws JsonMappingException, JsonProcessingException {
		System.out.println(value);
		
		String uri = "https://newsapi.org/v2/everything?q="
				+ value + "&apiKey=5b0473d941044694a5c0e6452934bcc1";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		
		System.out.println(result);
		
		ObjectMapper objectMapper = new ObjectMapper();
		News news1 = objectMapper.readValue(result, News.class); 
		
		model.addAttribute("articles", news1.getArticles());
		
		return "searchPages.html";
	}
	
	@GetMapping(value = "/contact")
	public String contact() {
		return "contact.html";
	}
	
	@GetMapping(value = "/about")
	public String about() {
		return "about.html";
	}
	
	@GetMapping(value = "/business")
	public String business(Model model) throws JsonMappingException, JsonProcessingException {
		
		category = "business";
		
		return "redirect:".concat("/searched");
	}
	
	@GetMapping(value = "/entertainment")
	public String entertainment(Model model) throws JsonMappingException, JsonProcessingException {
		
		category = "entertainment";
		
		return "redirect:".concat("/searched");
	}
	
	@GetMapping(value = "/general")
	public String general(Model model) throws JsonMappingException, JsonProcessingException {
		
		category = "general";
		
		return "redirect:".concat("/searched");
	}
	
	@GetMapping(value = "/health")
	public String health(Model model) throws JsonMappingException, JsonProcessingException {
		
		category = "health";
		
		return "redirect:".concat("/searched");
	}
	
	@GetMapping(value = "/science")
	public String science(Model model) throws JsonMappingException, JsonProcessingException {
		
		category = "science";
		
		return "redirect:".concat("/searched");
	}
	
	@GetMapping(value = "/sports")
	public String sports(Model model) throws JsonMappingException, JsonProcessingException {
		
		category = "sports";
		
		return "redirect:".concat("/searched");
	}
	
	@GetMapping(value = "/technology")
	public String technology(Model model) throws JsonMappingException, JsonProcessingException {
		
		category = "technology";
		
		return "redirect:".concat("/searched");
	}
	
	@RequestMapping(value = "/searched")
	public String common(Model model) throws JsonMappingException, JsonProcessingException {
		String uri = "https://newsapi.org/v2/top-headlines/sources?category="
			+ category	+ "&apiKey=5b0473d941044694a5c0e6452934bcc1&language=en";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		
		System.out.println(result);
		
		ObjectMapper objectMapper = new ObjectMapper();
		SpecNews news1 = objectMapper.readValue(result, SpecNews.class); 
		
		model.addAttribute("articles", news1.getSources());
		
		return "searched.html";
	}
}
