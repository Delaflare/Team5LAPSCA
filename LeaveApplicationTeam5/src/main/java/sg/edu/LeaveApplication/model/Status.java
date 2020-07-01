package sg.edu.LeaveApplication.model;

public enum Status {

PENDING("PENDING"), APPROVED("APPROVED"), REJECTED("REJECTED"), CANCELLED("CANCELLED"),UPDATED("UPDATED"), DELETED("DELECTED");
	
	private final String displayValue;
	
	private Status (String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
