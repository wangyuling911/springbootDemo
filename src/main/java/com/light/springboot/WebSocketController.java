package com.light.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebSocketController {


	@Autowired
	private WebSocket webSocketServer;


	// @Inner(value = false)
	@PostMapping("/pushCarWarningInfo")
	public String pushCarWarningInfo(String message) {
		webSocketServer.sendAllMessage(message);
		return "ok";

	}

}