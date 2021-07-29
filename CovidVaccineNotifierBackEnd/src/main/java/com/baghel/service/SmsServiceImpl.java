package com.baghel.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baghel.model.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsServiceImpl implements SmsService {

	@Value("${notifier.sidAccount}")
	private String ACCOUNT_SID;

	@Value("${notifier.authId}")
	private String AUTH_TOKEN;

	@Value("${notifier.fromNumber}")
	private String FROM_NUMBER;

	private static Logger logger = Logger.getLogger(SmsServiceImpl.class);

	public void send(SmsPojo sms) {

		logger.info("inside of SmsServiceImpl method send");
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage()).create();
		logger.info("invoked send method sent message to given number");

	}
}
