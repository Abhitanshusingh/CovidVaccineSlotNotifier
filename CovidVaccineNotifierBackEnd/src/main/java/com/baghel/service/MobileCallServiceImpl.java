package com.baghel.service;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

@Service
public class MobileCallServiceImpl implements MobileCallService {

	private static Logger logger = Logger.getLogger(MobileCallServiceImpl.class);

//	@Value("${notifier.sidAccount}")
	private String SID_ACCOUNT = "AC59547b92df5d93d3bea0d480da731098";

//	@Value("${notifier.authId}")
	private String AUTH_ID = "bf2677f36a6eb396f5b3d9b362c4e9c0";

	@Value("${notifier.fromNumber}")
	private String FROM_NUMBER;

	@Value("${notifier.toNumber}")
	private String TO_NUMBER;

	public void init() {
		Twilio.init(SID_ACCOUNT, AUTH_ID);
		logger.info("invoked init method");
	}

	public void run() throws Exception {
		logger.info("inside of MobileCallServiceImpl method run");
		Call.creator(new PhoneNumber(TO_NUMBER), new PhoneNumber(FROM_NUMBER),
				new URI("http://demo.twilio.com/docs/voice.xml")).create();
		logger.info("invoked run method calling to given number");
	}
}
