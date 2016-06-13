package com.nanoboat.util;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NanoboatUtil {

	static Logger logger = Logger.getLogger(NanoboatUtil.class.getName());

	public static boolean ValidatePhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern.compile("(\\d{3}-\\d{7})|(\\d{10})");
		Matcher matcher = pattern.matcher(phoneNumber);

		if (matcher.matches()) {
			logger.info("Phone Number Valid");
			return true;
		} else {
			logger.info("Phone Number is invalid");
			return false;
		}
	}

	
	public static void main(String[] args) {
		NanoboatUtil.ValidatePhoneNumber("9986218269");
	}
}
