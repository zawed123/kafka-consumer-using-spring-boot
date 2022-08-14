package com.monocept.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.model.User;

@RestController
@RequestMapping("/kafka")
public class HomeController {

	User userFromTopic = null;

	public HomeController() {

		System.out.println("Hello Controller");
	}

	@GetMapping("/consumeJsonMessage")
	public User consumeJsonMessage() {
		System.out.println(userFromTopic);
		return userFromTopic;
	}

	@KafkaListener(groupId = "test-topic-1", topics = "test-topic", containerFactory = "userKafkaListenerContainerFactory")
	public User getJsonMsgFromTopic(User user) {
		userFromTopic = user;
		return userFromTopic;
	}

}
