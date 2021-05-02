package budgetplanner.dto;

import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

public class PaymentsSearchResource {
	private String category;
	private LocalDate fromDate;
	private LocalDate toDate;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		if (fromDate != null && toDate == null) {
			return LocalDate.now();
		}
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public boolean isBlank() {
		return ObjectUtils.isEmpty(category) && ObjectUtils.isEmpty(toDate) && ObjectUtils.isEmpty(fromDate);
	}

	public boolean isCategoryOnly() {
		return !ObjectUtils.isEmpty(category) && ObjectUtils.isEmpty(toDate) && ObjectUtils.isEmpty(fromDate);
	}

	public boolean isDateOnly() {
		return ObjectUtils.isEmpty(category) && !ObjectUtils.isEmpty(toDate) && !ObjectUtils.isEmpty(fromDate);
	}

	public boolean isFull() {
		return !ObjectUtils.isEmpty(category) && !ObjectUtils.isEmpty(toDate) && !ObjectUtils.isEmpty(fromDate);
	}
}
