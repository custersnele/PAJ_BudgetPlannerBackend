package budgetplanner.reporting;

import be.pxl.paj.budgetplanner.entity.Payment;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MonthlyReportInformation {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");

	private String fullName;
	private Month month;
	private int year;
	private List<Payment> incoming;
	private List<Payment> outgoing;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Payment> getIncoming() {
		return incoming;
	}

	public void setIncoming(List<Payment> incoming) {
		this.incoming = incoming;
	}

	public List<Payment> getOutgoing() {
		return outgoing;
	}

	public void setOutgoing(List<Payment> outgoing) {
		this.outgoing = outgoing;
	}

	public double getTotalIncoming() {
		return incoming.stream().mapToDouble(Payment::getAmount).sum();
	}

	public double getTotalOutgoing() {
		return outgoing.stream().mapToDouble(Payment::getAmount).sum();
	}

	public double getDifference() {
		return getTotalIncoming() + getTotalOutgoing();
	}

	public String getFullDate() {
		LocalDate date = LocalDate.of(year, month, 1);
		return date.format(DATE_FORMATTER);
	}
}
