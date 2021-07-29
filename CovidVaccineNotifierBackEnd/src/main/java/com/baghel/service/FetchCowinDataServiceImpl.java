package com.baghel.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FetchCowinDataServiceImpl implements FetchCowinDataService {

	@Value("${cowin.api.url}")
	private String URl;

	private String currentAndNextDate;

	private String availableSlotData = "";

	private static Logger logger = LoggerFactory.getLogger(FetchCowinDataServiceImpl.class);

	private String getDataFromCowinAPI() throws Exception {
		StringBuilder sb = new StringBuilder();
		URL url = new URL(URl + currentAndNextDate);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		try {
			InputStream inputStream = urlConnection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String inputLine = bufferedReader.readLine();

			while (inputLine != null) {
				sb.append(inputLine);
				inputLine = bufferedReader.readLine();
			}
		} finally {
			urlConnection.disconnect();
		}
		String data = sb.toString();
		return data;
	}

	private boolean readCowinSessionData() throws Exception {
		int available_capacity = 0;
		String json = getDataFromCowinAPI();
		JSONObject obj;
		obj = new JSONObject(json);
		JSONArray results_arr = obj.getJSONArray("sessions");
		final int n = results_arr.length();
		for (int i = 0; i < n; ++i) {
			String place_name = results_arr.getJSONObject(i).getString("name");
			String address = results_arr.getJSONObject(i).getString("address");
			available_capacity = results_arr.getJSONObject(i).getInt("available_capacity");
			logger.info("Name: " + place_name);
			logger.info("Address :" + address);
			logger.info("Available_capacity :" + available_capacity);
			logger.info("-------------------------------------------------------------------");
			if (available_capacity > 0) {
				this.availableSlotData += "\n Name:" + place_name;
				this.availableSlotData += "\n Address:" + address;
				this.availableSlotData += "\n Available_capacity:" + available_capacity
						+ "\n---------------------------------------";
			}
		}
		if (available_capacity > 0)
			return true;
		return false;
	}

	private boolean getCurrnetDate() throws Exception {

		String currentDate = new SimpleDateFormat("dd-MM-YYYY").format(Calendar.getInstance().getTime());
		logger.info("for current date: " + currentDate + "\n");
		this.currentAndNextDate = currentDate;
		if (readCowinSessionData() == true)
			return true;
		return false;
	}

	private boolean getNextDate() throws Exception {
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-YYYY");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1); // number of days to add
		String tomorrowDate = (String) (formattedDate.format(c.getTime()));
		logger.info("for tomorrows date: " + tomorrowDate + "\n");
		this.currentAndNextDate = tomorrowDate;
		if (readCowinSessionData() == true)
			return true;
		return false;
	}

	public boolean checkForCurrentDateAndNextDate() throws Exception {
		logger.info("inside of FetchCowinDataServiceImpl checkForCurrentDateAndNextDate method ");
		logger.info("invoking getCurrnetDate method ");
		if (getCurrnetDate() == true)
			return true;
		logger.info("invoking getNextDate method ");
		if (getNextDate() == true)
			return true;
		return false;

	}
}
