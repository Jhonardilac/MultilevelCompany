package rules;

import java.util.Date;

import models.entities.Order;

public class Rules {
	
	public static final int BUSINESS_DAYS = 3;
	public static final long DAYS_TIME = 259200000;
	public static final long ONE_WEEK_TIME = 604800000;

	public static boolean validateOrderDelivery(Order order){
		return ((new Date().getTime() - order.getDate().getTime()) >= DAYS_TIME);
	}
	
	public static void main(String[] args) {
	}

	public static boolean validateReturnProduct(Order order) {
		return ((new Date().getTime() - order.getDate().getTime()) >= ONE_WEEK_TIME);
	}
}