package org.datum.util;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Timer {
	long start = new Date().getTime();
	
	public long getTime() {
		return new Date().getTime() - start;
	}
	
	public void end() {
		log.debug(String.format("processed in %.4f sec", (float) getTime() / 1000));
	}
}
