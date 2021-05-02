package be.pxl.paj.budgetplanner.dto;

public class Message {
	private String message;

	public Message(String message) {
		this.message = message;
	}

	public Message(Exception e) {
		this(e.getMessage());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
