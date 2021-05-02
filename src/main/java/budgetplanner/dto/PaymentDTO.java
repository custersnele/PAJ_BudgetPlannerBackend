package budgetplanner.dto;

import be.pxl.paj.budgetplanner.entity.Payment;

import java.time.LocalDate;


public class PaymentDTO {
    private Long id;
    private String name;
    private double amount;
    private CategoryDTO category;
    private LocalDate date;
    private String detail;

    public PaymentDTO(Payment payment) {
    	this.id = payment.getId();
    	this.name = payment.getName();
        this.date = payment.getDate();
        this.amount = payment.getAmount();
        this.detail = payment.getDetail();
        if (payment.getCategory() != null) {
	        this.category = new CategoryDTO(payment.getCategory());
        }
    }

	public Long getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDetail() {
		return detail;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public boolean hasCategory(String categoryName) {
    	return category.getName().equals(categoryName);
    }

}
