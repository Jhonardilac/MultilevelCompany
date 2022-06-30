package models.entities;

public enum Month {

	JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);
	
	private int days;
	
	private Month(int days) {
		this.days = days;
	}
	
	public int getDays(int year) {
		if (this.equals(Month.FEBRUARY)) {
			if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))){
				return 29;
			}
		}
		return days;
	}
	
}
