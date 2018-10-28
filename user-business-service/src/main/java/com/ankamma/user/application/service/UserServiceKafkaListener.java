package com.ankamma.user.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ankamma.user.application.feign.UserClient;
import com.ankamma.user.application.model.UserRequest;
import com.ankamma.user.application.model.UserResponse;

@Service
public class UserServiceKafkaListener {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceKafkaListener.class);

	@Autowired
	UserClient userClient;
	
	
	@KafkaListener(topics = "${message.topic.name}", containerFactory = "userRequestKafkaListenerContainerFactory")
    public void userRequestListener(UserRequest userRequest) {
		
		UserResponse userResponse=userClient.createUser(userRequest);
		
		logger.info("UserServiceListener class userRequestListener method end{}"+ userResponse.getId() );
       
      
    }
	


}
