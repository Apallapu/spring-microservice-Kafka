package com.ankamma.user.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ankamma.user.application.feign.UserClient;
import com.ankamma.user.application.model.UserExit;
import com.ankamma.user.application.model.UserList;
import com.ankamma.user.application.model.UserRequest;
import com.ankamma.user.application.model.UserResponse;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class UserService {

	@Value(value = "${message.topic.name}")
	private String topicName;

	@Autowired
	UserClient userClient;

	@Autowired
	UserServiceListener userServiceListener;

	@Autowired
	private KafkaTemplate<String, UserRequest> KafkaTemplate;

	public UserResponse createUser(UserRequest userRequest) {

		return userClient.createUser(userRequest);
	}

	public UserExit existsByUsername(String username) {

		return userClient.checkUserExit(username);

	}

	public UserExit existsByEmail(String email) {
		return userClient.checkEmailExit(email);
	}

	public UserList getUserNames(String username) {

		return userClient.getUserNames(username);
	}

	public List<UserList> getUserList() {
		return userClient.getUserList();
	}

	public void createUsersForActiveMq(UserRequest userRequest) {
		
		KafkaTemplate.send(topicName, userRequest);

	}

}
