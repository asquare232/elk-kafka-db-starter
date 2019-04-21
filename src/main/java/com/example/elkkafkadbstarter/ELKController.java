package com.example.elkkafkadbstarter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ELKController {

	private static final Logger LOG = Logger.getLogger(ELKController.class.getName());
	

	@Autowired
	RestTemplate restTemplete;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/elkdemo")
	public String helloWorld() {
		String response = "Hello user ! " + new Date();
		LOG.log(Level.INFO, response);
		return response;
	}
	
	@RequestMapping(value= "/exception")
	public String exception() {
		String exception = "";
		
		try {
			throw new Exception("Exception has Occurred");
		}catch(Exception exe) {
			exe.printStackTrace();
			LOG.error(exe);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			
			exe.printStackTrace(pw);
			
			String stackTrace = pw.toString();
			LOG.info("Exception :" + stackTrace);
			exception = stackTrace;			
		}
		return exception;
	}

}
