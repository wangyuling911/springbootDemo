package com.light.springboot;


import lombok.Data;

@Data
public class SocketMsg {

	private Integer type;
	private String fromUser;
	private String toUser;
	private String msg;
}
