package com.baghel.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface FetchCowinDataService {
	
	public boolean checkForCurrentDateAndNextDate() throws Exception;
	
	public String  availableSlotData = "";
	
}
