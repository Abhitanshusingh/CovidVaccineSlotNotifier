package com.baghel.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baghel.model.SmsPojo;
import com.baghel.model.UserDetails;
import com.baghel.service.FetchCowinDataService;
import com.baghel.service.MobileCallService;
import com.baghel.service.SmsService;
import com.baghel.service.UserService;

@RestController()
@CrossOrigin(origins="*")
public class CovidVaccineNotifierController {

	private static Logger logger = Logger.getLogger(CovidVaccineNotifierController.class);

	@Autowired
	private MobileCallService mobileCallService;

	@Autowired
	private FetchCowinDataService fetchCowinDataService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private SmsPojo smsPojo;
	
	@Autowired
	private UserService userService;

	@Value("${notifier.toNumber}")
	private String to;

	private String message = "";
	
	@PostMapping("/covidVaccineNotifier")
	private ResponseEntity<UserDetails> addUser(@RequestBody UserDetails user ) {
		return ResponseEntity.ok().body(userService.addUser(user));
	}
	
	public boolean checkSlotAvailable() throws Exception {
		logger.info("inside of CovidVaccineNotifierController method checkSlotAvailable");
		logger.info("invoking checkForCurrentDateAndNextDate");
		boolean ifDoseAvailable = fetchCowinDataService.checkForCurrentDateAndNextDate();
		logger.info("if dose is available: " + ifDoseAvailable);
		message = fetchCowinDataService.availableSlotData;
		logger.info("message: " + message);

		if (ifDoseAvailable) {
//			smsPojo.setTo(to);
//			smsPojo.setMessage(message);
//			logger.info("invoking send method to send message");
//			smsService.send(smsPojo);
			logger.info("invoking init method to initilize SID_ACCOUNT and AUTH_ID");
			mobileCallService.init();
			logger.info("invoking run method to call");
			mobileCallService.run();
			return true;
		}
		return false;
	}
	@Scheduled(cron = "* * 1 * * *")
	private String isSlotAlailable() throws Exception {
		boolean runUptoSlotAvailable = checkSlotAvailable();	
		return runUptoSlotAvailable ? "Slot is available in this area : \n"+message:"Slot not available";
	}
	
}
