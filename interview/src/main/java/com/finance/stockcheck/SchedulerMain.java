package com.finance.stockcheck;

import java.util.Timer;

/**
 * This is the main class from which the
 * scheduled task would be invoked.
 * @author Ramesh
 *
 */
public class SchedulerMain {

	public static void main(String[] args) {
		Timer timer = new Timer();
		//Scheduled task which would check the stock price in Yahoo and Google finance
		ScheduledTask task = new ScheduledTask(); 
		//Delay to be set in milliseconds
		timer.schedule(task, 0 ,Constants.TASK_DELAY);
	}
}
