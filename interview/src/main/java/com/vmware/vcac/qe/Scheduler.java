package com.vmware.vcac.qe;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
public class Scheduler {
   
   private final ScheduledExecutorService scheduler =
		   Executors.newScheduledThreadPool(1);
   
   private final PriceComparator  priceComparator =
		   new PriceComparator();
   
   private void init() {
	   priceComparator.setup();		
	}
   
   private void runForMarketHours() {
         
     final ScheduledFuture<?> handler =
       scheduler.scheduleAtFixedRate(priceComparator, 0, 300, SECONDS);
     
     scheduler.schedule(new Runnable() {
       public void run() { handler.cancel(true); }
     }, 1200, SECONDS);
   }
   
   private void shutDown() {
	   try {  
		while (!scheduler.awaitTermination(1260, SECONDS))
		   {
			 scheduler.shutdown();
			 priceComparator.tearDown();
		   }
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	finally {
		priceComparator.tearDown();
	}
   }
   
   public static void main(String[] args) {
	   Scheduler scheduler = new Scheduler();
	   scheduler.init();
	   scheduler.runForMarketHours();
	   scheduler.shutDown();
   }     
 }