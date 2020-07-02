package sg.edu.LeaveApplication.model;

public enum Time {

AM("AM"), PM("PM");
	
	private final String displayValue;
	
	private Time (String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
