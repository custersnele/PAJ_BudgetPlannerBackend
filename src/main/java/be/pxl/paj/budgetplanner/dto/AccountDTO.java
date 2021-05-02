package be.pxl.paj.budgetplanner.dto;

import be.pxl.paj.budgetplanner.entity.Account;

public class AccountDTO {
	private Long id;
	private String name;
	private String firstName;
	private String iban;
	private String displayName;

	public AccountDTO(Account account) {
		this.id = account.getId();
		this.name = account.getName();
		this.firstName = account.getFirstName();
		this.iban = account.getIban();
		displayName = account.getFirstName() + " " + account.getName() + " [" + account.getIban() + "]";
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getIban() {
		return iban;
	}

	public String getDisplayName() {
		return displayName;
	}
}
