package be.pxl.paj.budgetplanner.entity;

import java.time.LocalDate;
import java.time.Month;

public class Payment {
    private Long id;
    private Account account;
    private String name;
    private double amount;
    private Category category;
    private LocalDate date;
    private String detail;

    public Payment() {
        // JPA only
    }

    public Payment(LocalDate date, String name, double amount, String detail) {
        this.date = date;
        this.amount = amount;
        this.detail = detail;
        this.name = name;
    }

	public boolean isIncome() {
		return amount > 0;
	}

	public boolean isExpense() {
		return amount < 0;
	}

	public boolean isMonthAndYear(Month month, int year) {
		return date.getMonth()  == month && date.getYear() == year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public boolean hasLabel(Category category) {
        return category.equals(this.category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Payment payment = (Payment) o;

        return getId().equals(payment.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

	@Override
	public String toString() {
		return "Payment{" +
				"id=" + id +
				", account=" + account +
				", name='" + name + '\'' +
				", amount=" + amount +
				", category=" + category +
				", date=" + date +
				", detail='" + detail + '\'' +
				'}';
	}

	public void delete() {
        account.removePayment(this);
    }
}
