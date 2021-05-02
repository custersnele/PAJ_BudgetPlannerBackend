package budgetplanner.exception;

public class AccountNotFoundException  extends RuntimeException {

	public AccountNotFoundException(String nameOrIban) {
		super("Account  [" + nameOrIban + "] does not exist.");
	}

	public AccountNotFoundException(Long accountId) {
		super("Account with id [" + accountId + "] does not exist.");
	}
}
