package be.pxl.paj.budgetplanner.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private Long id;
	private String iban;
	private String firstName;
	private String name;
	private List<Payment> payments = new ArrayList<>();

	public Account() {
		// JPA only
	}

	public Account(String iban, String firstName, String name) {
		this.iban = iban;
		this.firstName = firstName;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getIban() {
		return iban;
	}

	public String getFirstName() {
		return firstName;
	}

	public List<Payment> getTransactions() {
		return payments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", iban='" + iban + '\'' +
				", firstName='" + firstName + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	public void addPayment(Payment payment) {
		payment.setAccount(this);
		payments.add(payment);
	}

	public void removePayment(Payment payment) {
		payments.remove(payment);
		payment.setAccount(null);
	}
}
