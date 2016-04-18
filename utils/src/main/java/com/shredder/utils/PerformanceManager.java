package com.shredder.utils;

import java.util.Date;
import java.util.Hashtable;

public class PerformanceManager {
	private static final ShredderLog log = new ShredderLog(PerformanceManager.class);
	private final static Hashtable<String, Date> startTimes = new Hashtable<>();
	private final static Date initDate = new Date();

	PerformanceManager() {
		log.v("Init at " + initDate);
	}

	public static void start(String operationName) {
		startTimes.put(operationName, new Date());
		log.i(operationName + " started");
	}

	public static void stop(String operationName) {
		if (startTimes.containsKey(operationName)) {
			Date startTime = startTimes.get(operationName);
			startTimes.remove(operationName);
			log.i(operationName + " completed (total time: " + (new Date().getTime() - startTime.getTime()) + "ms)");
		}
	}

	public static void logWithTime(String operationName) {
		log.i(operationName + " (" + (new Date().getTime() - initDate.getTime()) + "ms after init)");
	}
}
