package com.baghel.service;

import org.springframework.stereotype.Service;

import com.baghel.model.SmsPojo;

@Service
public interface SmsService {
	
	public void send(SmsPojo sms);
	
}
