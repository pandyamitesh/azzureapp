package com.cms.client.azureappclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home() throws UnknownHostException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String userName = "CSRA"; 



    	Date date = new Date();
				String myvar = "<!DOCTYPE html>"+
				"<html>"+
				"<head>"+
				"<style>"+
				"body {"+
				"    margin: 0;"+
				"}"+
				""+
				"ul {"+
				"    list-style-type: none;"+
				"    margin: 0;"+
				"    padding: 0;"+
				"    width: 25%;"+
				"    background-color: #f1f1f1;"+
				"    position: fixed;"+
				"    height: 100%;"+
				"    overflow: auto;"+
				"}"+
				""+
				"li a {"+
				"    display: block;"+
				"    color: #000;"+
				"    padding: 8px 16px;"+
				"    text-decoration: none;"+
				"}"+
				""+
				"li a.active {"+
				"    background-color: #4CAF50;"+
				"    color: white;"+
				"}"+
				""+
				"li a:hover:not(.active) {"+
				"    background-color: #555;"+
				"    color: white;"+
				"}"+
				"</style>"+
				"</head>"+
				"<body>"+
				""+
				"<ul>"+
				"  <li><a class=\"active\" href=\"#home\">Home</a></li>"+
				"  <li><a href=\"#news\">News</a></li>"+
				"  <li><a href=\"#contact\">Contact</a></li>"+
				"  <li><a href=\"#about\">About</a></li>"+
				"</ul>"+
				""+
				"<div style=\"margin-left:25%;padding:1px 16px;height:1000px;\">"+
				"  <h2>Hello "+userName+" </h2>"+
				"  <h3>This Application is Served from </h3> <h1 style=\"border: 2px solid Tomato;\"> Host Name :- "+  InetAddress.getLocalHost().getHostName()
				+"<h1 style=\"border: 2px solid DodgerBlue;\"> IP Address :- "+  InetAddress.getLocalHost().getHostAddress()
				+"<h1 style=\"border: 2px solid Violet;\"> Date Time :- "+ dateFormat.format(date) 
				+"</div>"+
				""+
				"</body>"+
				"</html>";
	

        return myvar; 
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private RabbitTemplate template;

	@RequestMapping("/r1")
	public String chaining() {

		Object a = template.convertSendAndReceive("azzureapp.rpc", "pong", "ping");
		return (a.toString());
		
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Value("${azzureserverms.host}")
	private String azzureserverMSHost;

	@Value("${azzureserverms.port}")
	private String azzureserverMSPort;

	@RequestMapping("/h1")
	public String webchaining() {
		String URL = "http://" + azzureserverMSHost + ":" + azzureserverMSPort ;
		ResponseEntity<String> response = restTemplate().getForEntity(URL, String.class);
		return "Chaining " + response.getBody();
		
	}

}
