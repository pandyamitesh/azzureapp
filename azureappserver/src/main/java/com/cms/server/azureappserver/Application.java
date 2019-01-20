package com.cms.server.azureappserver;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;


@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
    public String home() throws UnknownHostException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Date date = new Date();
				String myvar = "This is server  " + dateFormat.format(date) ;
	

        return myvar; 
	}
	
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "azzureapp.rpc.requests", durable = "true"), exchange = @Exchange(value = "azzureapp.rpc", ignoreDeclarationExceptions = "true"), key = "pong"))
	public String pong(String requestJson) throws Exception {
		
		return requestJson + " pong ";
	}

}



